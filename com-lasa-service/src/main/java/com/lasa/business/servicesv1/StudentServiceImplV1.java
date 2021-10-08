/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.servicesv1;

import com.lasa.data.entity.Student;
import com.lasa.data.repository.StudentRepository;
import java.util.List;

import com.lasa.business.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hai
 */
@Component
@Qualifier("StudentServiceImplV1")
public class StudentServiceImplV1 implements StudentService {
    
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImplV1(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findByStudentId(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student createStudent(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Student updateStudent(Student updateStudent) {
        
        Student student = studentRepository.findById(updateStudent.getId()).get();
        
        if(updateStudent.getMssv() != null) 
            student.setMssv(updateStudent.getMssv());
        
        if(updateStudent.getMajorId() != null) 
            student.setMajorId(updateStudent.getMajorId());
        
        if(updateStudent.getName() != null)
            student.setName(updateStudent.getName());
        
        if(updateStudent.getPhone() != null)
            student.setPhone(updateStudent.getPhone());
        
        if(updateStudent.getBirthday() != null)
            student.setBirthday(updateStudent.getBirthday());
        
        if(updateStudent.getGender() != null)
            student.setGender(updateStudent.getGender());
        
        if(updateStudent.getAddress() != null)
            student.setAddress(updateStudent.getAddress());
        
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudents(List<Integer> ids) {
        studentRepository.deleteAllById(ids);
    }

    @Override
    public Page<Student> findBasicInformationLecturer(Integer page, Integer size) {
        return studentRepository.findBasicInformationStudent(PageRequest.of(page, size));
    }
    
}
