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
import com.lasa.data.model.utils.projection.SimpleAdmin;
import com.lasa.data.repo.repository.AdminRepository;
import com.lasa.data.repo.repository.LecturerRepository;
import com.lasa.data.repo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public Admin findByAdminId(Integer id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public SimpleAdmin findAdminWithoutPasswordById(Integer id) {
        return adminRepository.findById(id, SimpleAdmin.class).orElse(null);
    }

    @Override
    public List<Student> listAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public List<Lecturer> listAllLecturer() {
        return lecturerRepository.findAll();
    }

    @Override
    public Student findStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Lecturer findLecturerById(Integer id) {
        return lecturerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStudents(List<Integer> ids) {
       
    }

    @Override
    public void deleteLecturers(List<Integer> ids) {
        
    }
    
}
