/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.Admin;
import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.Student;
import com.lasa.data.entity.utils.projection.SimpleAdmin;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Admin
 */

@Service
public interface AdminService {

    Admin findByAdminId(Integer id);

    SimpleAdmin findAdminWithoutPasswordById(Integer id);

    List<Student> listAllStudent();

    List<Lecturer> listAllLecturer();

    Student findStudentById(Integer id);

    Lecturer findLecturerById(Integer id);

    void deleteStudents(List<Integer> ids);

    void deleteLecturers(List<Integer> ids);
}
