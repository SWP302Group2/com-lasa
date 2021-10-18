package com.lasa.security.appuser;

import com.lasa.data.entity.Admin;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.Student;
import com.lasa.data.repo.repository.AdminRepository;
import com.lasa.data.repo.repository.LecturerRepository;
import com.lasa.data.repo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public MyUserDetailsService(StudentRepository studentRepository, LecturerRepository lecturerRepository, AdminRepository adminRepository) {
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> student = studentRepository.findStudentByEmail(username);
        Optional<Lecturer> lecturer = lecturerRepository.findLecturerByEmail(username);
        Optional<Admin> admin = adminRepository.findAdminByUsername(username);

        if(student.isPresent()) {
            return student.map(MyUserDetails::new).get();
        }
        else if(lecturer.isPresent()) {
            return lecturer.map(MyUserDetails::new).get();
        }
        else if(admin.isPresent()) {
            return admin.map(MyUserDetails::new).get();
        }else {
            throw new UsernameNotFoundException(String.format("%s not found", username));
        }
    }
}
