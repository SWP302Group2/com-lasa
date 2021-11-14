package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.AdminOperations;
import com.lasa.business.controllers.utils.authorization.IsAdmin;
import com.lasa.business.controllers.utils.authorization.IsNotAvailable;
import com.lasa.business.services.AdminService;
import com.lasa.data.model.request.AdminRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public class AdminController implements AdminOperations {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    @IsAdmin
    @PreAuthorize("#id.equals(authentication.principle.id)")
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
    @PreAuthorize("#admin.id.equals(authentication.principle.id)")
    public ResponseEntity<?> updateAdmin(AdminRequestModel admin) {
        return ResponseEntity.ok(adminService.updateAdmin(admin));
    }

    @Override
    @IsNotAvailable
    public ResponseEntity<?> deleteAdmin(List<Integer> id) {
        return null;
    }
}
