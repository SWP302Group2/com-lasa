/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.Admin;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.Student;
import java.util.List;

import com.lasa.data.entity.utils.AdminSimple;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author Admin
 */

@Service
public interface AdminService {

    Admin findByAdminId(Integer id);

    AdminSimple findAdminWithoutPasswordById(Integer id);

    List<Student> listAllStudent();

    List<Lecturer> listAllLecturer();

    Student findStudentById(Integer id);

    Lecturer findLecturerById(Integer id);

    void deleteStudents(List<Integer> ids);

    void deleteLecturers(List<Integer> ids);
}
