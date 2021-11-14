/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.Student;
import com.lasa.data.model.request.AdminRequestModel;
import com.lasa.data.model.utils.projection.SimpleAdmin;
import com.lasa.data.model.view.AdminViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Admin
 */

@Service
public interface AdminService {

    AdminViewModel findByAdminId(Integer id);

    AdminViewModel updateAdmin(AdminRequestModel model);

}