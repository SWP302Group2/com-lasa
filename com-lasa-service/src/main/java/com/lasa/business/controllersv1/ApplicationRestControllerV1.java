package com.lasa.business.controllersv1;

import com.lasa.business.controllers.ApplicationOperations;
import com.lasa.business.services.AdminService;
import com.lasa.business.services.LecturerService;
import com.lasa.business.services.StudentService;
import com.lasa.security.appuser.MyUserDetails;
import com.lasa.security.model.InformationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lasa.security.permission.ApplicationUserRole.*;

@RestController
@RequestMapping("/api/v1/home")
public class ApplicationRestControllerV1 implements ApplicationOperations {

    private final StudentService studentService;
    private final LecturerService lecturerService;
    private final AdminService adminService;

    @Autowired
    public ApplicationRestControllerV1(StudentService studentService, LecturerService lecturerService, AdminService adminService) {
        this.studentService = studentService;
        this.lecturerService = lecturerService;
        this.adminService = adminService;
    }

    @Override
    public ResponseEntity<?> getUserInformation(MyUserDetails myUserDetails) {
        Integer id = myUserDetails.getId();
        Boolean isStudent = myUserDetails.getAuthorities()
                .stream()
                .filter(t -> t.getAuthority().equals("ROLE_" + STUDENT))
                .findAny()
                .isPresent();

        Boolean isLecturer = myUserDetails.getAuthorities()
                .stream()
                .filter(t -> t.getAuthority().equals("ROLE_" + LECTURER.name()))
                .findAny()
                .isPresent();

        if(isStudent) {
            return ResponseEntity.ok(InformationResponse.builder()
                    .information(studentService.findByStudentId(id))
                    .role("ROLE_" + STUDENT.name())
                    .build());
        }else if(isLecturer) {
            return ResponseEntity.ok(InformationResponse.builder()
                    .information(lecturerService.findLecturerById(id))
                    .role("ROLE_" + LECTURER.name())
                    .build());
        }else {
            return ResponseEntity.ok(InformationResponse.builder()
                    .information(adminService.findAdminWithoutPasswordById(id))
                    .role("ROLE_" + ADMIN.name())
                    .build());
        }
    }
}
