package com.lasa.business.servicesv1;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.lasa.business.config.utils.ValidationHelper;
import com.lasa.business.services.AuthenticationService;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.Student;
import com.lasa.data.repository.LecturerRepository;
import com.lasa.data.repository.StudentRepository;
import com.lasa.security.jwt.JwtUtil;
import com.lasa.security.model.AuthenticationRequest;
import com.lasa.security.model.GoogleAuthenticationRequest;
import com.lasa.security.model.InformationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static com.lasa.business.config.utils.ValidationHelper.emailValidation;
import static com.lasa.security.permission.ApplicationUserRole.LECTURER;
import static com.lasa.security.permission.ApplicationUserRole.STUDENT;

@Component
@Qualifier("AuthenticationServiceImplV1")
public class AuthenticationServiceImplV1 implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private final JwtUtil jwtUtil;
    private final String CLIENT_ID1 = "69016056321-5j2fr23vo8oggc3jsqksgu2a4g1s1mhn.apps.googleusercontent.com";
    private final String CLIENT_ID2 = "431681257434-1makcgok3vs3tnivthtvcbnt2fcc1aul.apps.googleusercontent.com";

    @Autowired
    public AuthenticationServiceImplV1(AuthenticationManager authenticationManager,
                                       @Qualifier("MyUserDetailsService") UserDetailsService userDetailsService,
                                       JwtUtil jwtUtil,
                                       StudentRepository studentRepository,
                                       LecturerRepository lecturerRepository
                                       ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
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

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return jwt;
    }

    @Override
    public String authenticateGoogleAccount(GoogleAuthenticationRequest googleAuthenticationRequest,String role)
            throws GeneralSecurityException, IOException, EmailDomainException {
        GoogleIdToken idToken = getIdToken(googleAuthenticationRequest);

        if(idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();

            if (!emailValidation(email))
                throw new EmailDomainException("INVALID_EMAIL_DOMAIN");
            try{
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                return jwtUtil.generateToken(userDetails);
            }catch (UsernameNotFoundException ex) {
                //if role !=null create new user by role student or lecturer
                if(role != null) {
                    if(role.equalsIgnoreCase(STUDENT.name())) {
                        String name = (String) payload.get("name");
                        String pictureUrl = (String) payload.get("picture");
                        Student student = Student.builder()
                                .email(email)
                                .name(name)
                                .status(1) //set student to active
                                .avatarUrl(pictureUrl)
                                .build();
                        //save student to database
                        studentRepository.saveAndFlush(student);
                        //load user from database again to make sure user already in db and for generate user detail
                        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                        return jwtUtil.generateToken(userDetails);
                    }
                    if(role.equalsIgnoreCase(LECTURER.name())) {
                        String name = (String) payload.get("name");
                        String pictureUrl = (String) payload.get("picture");
                        Lecturer lecturer = Lecturer.builder()
                                .email(email)
                                .name(name)
                                .status(0) //set lecturer status to inactive
                                .avatarUrl(pictureUrl)
                                .build();
                        lecturerRepository.saveAndFlush(lecturer);
                        //load user from database again to make sure user already in db and for generate user detail
                        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                        return jwtUtil.generateToken(userDetails);
                    }
                }
            }
        }

        return null;
    }

    @Override
    public InformationResponse emailVerify(GoogleAuthenticationRequest googleAuthenticationRequest) throws GeneralSecurityException, IOException, EmailDomainException, UserAlreadyExistException {
        GoogleIdToken idToken = getIdToken(googleAuthenticationRequest);
        if(idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            if(!emailValidation(email)) {
                throw new EmailDomainException("INVALID_EMAIL_DOMAIN");
            } else {
                Optional<Student> student = studentRepository.findStudentByEmail(email);
                Optional<Lecturer> lecturer = lecturerRepository.findLecturerByEmail(email);
                if(student.isPresent() || lecturer.isPresent()) throw new UserAlreadyExistException("EMAIL_ALREADY_EXIST");
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

    public class EmailDomainException extends Exception{
        public EmailDomainException(String message) {
            super(message);
        }
    }

    public class UserAlreadyExistException extends Exception{
        public UserAlreadyExistException(String message) {
            super(message);
        }
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
        GoogleIdToken idToken = verifier.verify(googleAuthenticationRequest.getToken());
        return idToken;
    }
}
