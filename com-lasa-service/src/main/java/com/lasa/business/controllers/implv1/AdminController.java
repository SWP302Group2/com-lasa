package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.AdminOperations;
import com.lasa.business.controllers.utils.authorization.IsAdmin;
import com.lasa.business.controllers.utils.authorization.IsNotAvailable;
import com.lasa.business.services.AdminService;
import com.lasa.data.model.request.AdminRequestModel;
import com.lasa.security.appuser.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/admins")
public class AdminController implements AdminOperations {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    @IsAdmin
    @PreAuthorize("#id.equals(authentication.principal.id)")
    public ResponseEntity<?> findById(Integer id) {
        return ResponseEntity.ok(adminService.findByAdminId(id));
    }

    @Override
    @IsNotAvailable
    public ResponseEntity<?> createAdmin(AdminRequestModel adminRequestModel) {
        return null;
    }

    @Override
    @IsAdmin
    @PreAuthorize("#admin.id.equals(authentication.principal.id)")
    public ResponseEntity<?> updateAdmin(AdminRequestModel admin) {
        return ResponseEntity.ok(adminService.updateAdmin(admin));
    }

    @Override
    @IsNotAvailable
    public ResponseEntity<?> deleteAdmin(List<Integer> id) {
        return null;
    }
}
