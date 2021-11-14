/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.AdminService;
import com.lasa.data.model.entity.Admin;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.Student;
import com.lasa.data.model.request.AdminRequestModel;
import com.lasa.data.model.utils.projection.SimpleAdmin;
import com.lasa.data.model.view.AdminViewModel;
import com.lasa.data.repo.repository.AdminRepository;
import com.lasa.data.repo.repository.LecturerRepository;
import com.lasa.data.repo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Admin
 */

@Component
@Qualifier("AdminServiceImplV1")
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, StudentRepository studentRepository, LecturerRepository lecturerRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public AdminViewModel findByAdminId(Integer id) {
        return new AdminViewModel(adminRepository.findById(id).get());
    }

    @Override
    @Transactional
    public AdminViewModel updateAdmin(AdminRequestModel model) {
        Admin admin = adminRepository.findById(model.getId()).get();
        if(Objects.nonNull(model.getAvatarUrl()))
            admin.setAvatarUrl(model.getAvatarUrl());

        if(Objects.nonNull(model.getBirthday()))
            admin.setBirthday(model.getBirthday());

        if(Objects.nonNull(model.getGender()))
            admin.setGender(model.getGender());

        if(Objects.nonNull(model.getName()))
            admin.setName(model.getName());

        if(Objects.nonNull(model.getAvatarUrl()))
            admin.setAvatarUrl(model.getAvatarUrl());

        if(Objects.nonNull(model.getPhone()))
            admin.setPhone(model.getPhone());

        if(Objects.nonNull(model.getPassword()))
            admin.setPassword(model.getPassword());

        return new AdminViewModel(adminRepository.save(admin));
    }

}
