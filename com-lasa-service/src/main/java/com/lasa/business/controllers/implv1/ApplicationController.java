package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.ApplicationOperations;
import com.lasa.business.services.AdminService;
import com.lasa.business.services.LecturerService;
import com.lasa.business.services.StudentService;
import com.lasa.data.model.view.AdminViewModel;
import com.lasa.security.appuser.MyUserDetails;
import com.lasa.security.utils.model.InformationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import static com.lasa.security.utils.permission.ApplicationUserRole.*;

@RestController
@RequestMapping("/api/v1/home")
@ApiIgnore
public class ApplicationController implements ApplicationOperations {

    private final StudentService studentService;
    private final LecturerService lecturerService;
    private final AdminService adminService;

    @Autowired
    public ApplicationController(
            @Qualifier("StudentServiceImplV1") StudentService studentService,
            @Qualifier("LecturerServiceImplV1") LecturerService lecturerService,
            @Qualifier("AdminServiceImplV1") AdminService adminService
    ) {
        this.studentService = studentService;
        this.lecturerService = lecturerService;
        this.adminService = adminService;
    }

    @Override
    public ResponseEntity<?> getUserInformation() {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
                    .information(new AdminViewModel(adminService.findByAdminId(id)))
                    .role("ROLE_" + ADMIN.name())
                    .build());
        }
    }
}
