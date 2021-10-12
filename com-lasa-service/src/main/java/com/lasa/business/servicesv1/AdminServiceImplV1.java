/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.servicesv1;

import com.lasa.data.entity.Admin;
import com.lasa.data.repository.AdminRepository;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.repository.LecturerRepository;
import com.lasa.data.entity.Student;
import com.lasa.data.repository.StudentRepository;
import java.util.List;

import com.lasa.business.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */

@Component
@Qualifier("AdminServiceImplV1")
public class AdminServiceImplV1 implements AdminService {

    private final AdminRepository adminRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;

    @Autowired
    public AdminServiceImplV1(AdminRepository adminRepository, StudentRepository studentRepository, LecturerRepository lecturerRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Admin findByAdminId(Integer id) {
        return adminRepository.findAdminByIdWithoutPassword(id).orElse(null);
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
        return studentRepository.findById(id).get();
    }

    @Override
    public Lecturer findLecturerById(Integer id) {
        return lecturerRepository.findById(id).get();
    }

    @Override
    public void deleteStudents(List<Integer> ids) {
       
    }

    @Override
    public void deleteLecturers(List<Integer> ids) {
        
    }
    
}
