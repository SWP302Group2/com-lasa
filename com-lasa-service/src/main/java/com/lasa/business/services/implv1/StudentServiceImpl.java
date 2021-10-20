/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.StudentService;
import com.lasa.data.dto.StudentDTO;
import com.lasa.data.entity.Student;
import com.lasa.data.entity.utils.criteria.StudentSearchCriteria;
import com.lasa.data.entity.utils.page.StudentPage;
import com.lasa.data.entity.utils.specification.StudentSpecification;
import com.lasa.data.repo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author hai
 */
@Component
@Qualifier("StudentServiceImplV1")
public class StudentServiceImpl implements StudentService {
    
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Page<StudentDTO> findWithArgument(StudentSearchCriteria searchCriteria, StudentPage studentPage) {
        Pageable pageable = PageRequest.of(studentPage.getPage(), studentPage.getSize(), Sort.by(studentPage.getOrderBy(), studentPage.getSortBy()));
        return studentRepository
                .findAll(StudentSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new StudentDTO(t));
    }

    @Override
    public List<StudentDTO> findWithArgument(StudentSearchCriteria searchCriteria) {
        return studentRepository
                .findAll(StudentSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new StudentDTO(t))
                .collect(Collectors.toList());
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
    
}
