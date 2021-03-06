package com.lasa.business.services.implv1;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.lasa.business.services.AuthenticationService;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.LecturerTopicDetail;
import com.lasa.data.model.entity.Student;
import com.lasa.data.model.entity.Topic;
import com.lasa.data.repo.repository.LecturerRepository;
import com.lasa.data.repo.repository.LecturerTopicDetailRepository;
import com.lasa.data.repo.repository.StudentRepository;
import com.lasa.security.appuser.MyUserDetails;
import com.lasa.security.utils.exception.ExceptionUtils;
import com.lasa.security.utils.jwt.JwtUtil;
import com.lasa.security.utils.model.AuthenticationRequest;
import com.lasa.security.utils.model.GoogleAuthenticationRequest;
import com.lasa.security.utils.model.InformationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

import static com.lasa.business.config.utils.ValidationHelper.emailValidation;
import static com.lasa.security.utils.permission.ApplicationUserRole.LECTURER;
import static com.lasa.security.utils.permission.ApplicationUserRole.STUDENT;

@Component
@Qualifier("AuthenticationServiceImplV1")
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private final LecturerTopicDetailRepository lecturerTopicDetailRepository;
    private final JwtUtil jwtUtil;
    private final String CLIENT_ID1 = "69016056321-5j2fr23vo8oggc3jsqksgu2a4g1s1mhn.apps.googleusercontent.com";
    private final String CLIENT_ID2 = "431681257434-1makcgok3vs3tnivthtvcbnt2fcc1aul.apps.googleusercontent.com";

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     @Qualifier("MyUserDetailsService") UserDetailsService userDetailsService,
                                     JwtUtil jwtUtil,
                                     StudentRepository studentRepository,
                                     LecturerRepository lecturerRepository,
                                     LecturerTopicDetailRepository lecturerTopicDetailRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
        this.lecturerTopicDetailRepository = lecturerTopicDetailRepository;
    }

    @Override
    public String authenticateUsernameAndPassword(AuthenticationRequest authenticationRequest) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("INCORRECT_USERNAME_OR_PASSWORD");
        }

        final MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        return jwtUtil.generateToken(userDetails);
    }

    @Override
    public String authenticateGoogleAccount(GoogleAuthenticationRequest googleAuthenticationRequest,String role)
            throws GeneralSecurityException, IOException, ExceptionUtils.EmailDomainException {
        GoogleIdToken idToken = getIdToken(googleAuthenticationRequest);

        if(idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();

            if (!emailValidation(email))
                throw new ExceptionUtils.EmailDomainException("INVALID_EMAIL_DOMAIN");
            try{
                MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(email);
                return jwtUtil.generateToken(userDetails);
            }catch (UsernameNotFoundException ex) {
                //if role !=null create new user by role student or lecturer
                if(role != null) {

                    if(role.equalsIgnoreCase(STUDENT.name())) {
                        studentSignup(payload, googleAuthenticationRequest);

                        //load user from database again to make sure user already in db and for generate user detail
                        MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(email);
                        return jwtUtil.generateToken(userDetails);
                    }

                    if(role.equalsIgnoreCase(LECTURER.name())) {
                        lecturerSignup(payload, googleAuthenticationRequest);
                        MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(email);
                        Integer lecturerId = userDetails.getId();

                        if(Objects.nonNull(googleAuthenticationRequest.getTopics()))
                            addLecturerTopic(googleAuthenticationRequest, lecturerId);

                        return jwtUtil.generateToken(userDetails);
                    }
                }
            }
        }

        return null;
    }

    private void addLecturerTopic(GoogleAuthenticationRequest googleAuthenticationRequest, Integer lecturerId) {
        Collection<LecturerTopicDetail> topics = new ArrayList<>();
        googleAuthenticationRequest.getTopics().stream()
                .forEach(t -> topics.add(LecturerTopicDetail.builder()
                        .topic(Topic.builder()
                                .id(t)
                                .build())
                        .lecturer(Lecturer.builder()
                                .id(lecturerId)
                                .build())
                        .build()));
        lecturerTopicDetailRepository.saveAllAndFlush(topics);
    }

    private void studentSignup(GoogleIdToken.Payload payload, GoogleAuthenticationRequest googleAuthenticationRequest) {
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        String majorId = null;
        if(Objects.nonNull(googleAuthenticationRequest.getName()))
            name = googleAuthenticationRequest.getName();

        if(Objects.nonNull(googleAuthenticationRequest.getAvatarUrl()))
            pictureUrl = googleAuthenticationRequest.getAvatarUrl();

        if(Objects.nonNull(googleAuthenticationRequest.getMajorId()))
            majorId = googleAuthenticationRequest.getMajorId();

        Student student = Student.builder()
                .email(payload.getEmail())
                .name(name)
                .mssv(googleAuthenticationRequest.getMssv())
                .status(1) //set student to active
                .majorId(majorId)
                .avatarUrl(pictureUrl)
                .build();
        //save student to database
        studentRepository.saveAndFlush(student);
    }

    private void lecturerSignup(GoogleIdToken.Payload payload, GoogleAuthenticationRequest googleAuthenticationRequest) {
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");

        if(Objects.nonNull(googleAuthenticationRequest.getName()))
            name = googleAuthenticationRequest.getName();

        if(Objects.nonNull(googleAuthenticationRequest.getAvatarUrl()))
            pictureUrl = googleAuthenticationRequest.getAvatarUrl();

        System.out.println("Topics:" + googleAuthenticationRequest.getTopics());

        Lecturer lecturer = Lecturer.builder()
                .email(payload.getEmail())
                .name(name)
                .meetingUrl(googleAuthenticationRequest.getMeetUrl())
                .status(0) //set lecturer status to inactive
                .avatarUrl(pictureUrl)
                .build();
        lecturerRepository.saveAndFlush(lecturer);
    }

    @Override
    public InformationResponse emailVerify(GoogleAuthenticationRequest googleAuthenticationRequest) throws GeneralSecurityException, IOException, ExceptionUtils.EmailDomainException, ExceptionUtils.UserAlreadyExistException {
        GoogleIdToken idToken = getIdToken(googleAuthenticationRequest);
        if(idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            if(!emailValidation(email)) {
                throw new ExceptionUtils.EmailDomainException("INVALID_EMAIL_DOMAIN");
            } else {
                Optional<Student> student = studentRepository.findStudentByEmail(email);
                Optional<Lecturer> lecturer = lecturerRepository.findLecturerByEmail(email);
                if(student.isPresent() || lecturer.isPresent()) throw new ExceptionUtils.UserAlreadyExistException("EMAIL_ALREADY_EXIST");
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                return InformationResponse.builder()
                        .name(name)
                        .avatarUrl(pictureUrl)
                        .googleToken(googleAuthenticationRequest.getToken())
                        .email(email)
                        .build();
            }
        }
        return null;
    }





    private GoogleIdToken getIdToken(GoogleAuthenticationRequest googleAuthenticationRequest) throws GeneralSecurityException, IOException {
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .setAudience(Arrays.asList(CLIENT_ID1, CLIENT_ID2))
                .build();
        return verifier.verify(googleAuthenticationRequest.getToken());
    }
}
