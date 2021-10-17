/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.Student;
import com.lasa.data.entity.utils.criteria.StudentSearchCriteria;
import com.lasa.data.entity.utils.page.StudentPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */
@Service
public interface StudentService {
    
    Page<Student> findAll(StudentSearchCriteria searchCriteria, StudentPage studentPage);

    List<Student> findAll(StudentSearchCriteria searchCriteria);
    
    Student findByStudentId(Integer id);
    
    Student createStudent(Student student);
    
    Student updateStudent(Student student);
    
    void deleteStudents(List<Integer> ids);
}
