/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.Student;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author hai
 */
@Service
public interface StudentService {
    
    public List<Student> findAll();
    
    public Student findByStudentId(Integer id);
    
    // Create one student, end return object
    public Student createStudent(Student student);
    
    public Student updateStudent(Student student);
    
    public void deleteStudents(List<Integer> ids);
}
