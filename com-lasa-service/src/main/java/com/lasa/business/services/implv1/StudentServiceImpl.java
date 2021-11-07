/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.StudentService;
import com.lasa.data.model.entity.Student;
import com.lasa.data.model.request.StudentRequestModel;
import com.lasa.data.model.utils.criteria.StudentSearchCriteria;
import com.lasa.data.model.utils.page.StudentPage;
import com.lasa.data.model.utils.specification.StudentSpecification;
import com.lasa.data.model.view.StudentViewModel;
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
import java.util.Optional;
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
    public Page<StudentViewModel> findWithArgument(StudentSearchCriteria searchCriteria, StudentPage studentPage) {
        Pageable pageable = PageRequest.of(studentPage.getPage(), studentPage.getSize(), Sort.by(studentPage.getOrderBy(), studentPage.getSortBy()));
        return studentRepository
                .findAll(StudentSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new StudentViewModel(t));
    }

    @Override
    public List<StudentViewModel> findWithArgument(StudentSearchCriteria searchCriteria) {
        return studentRepository
                .findAll(StudentSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new StudentViewModel(t))
                .collect(Collectors.toList());
    }

    @Override
    public StudentViewModel findByStudentId(Integer id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent())
            return new StudentViewModel(student.get());
        return null;
    }

    @Override
    public StudentViewModel createStudent(StudentRequestModel student) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public StudentViewModel updateStudent(StudentRequestModel updateStudent) {
        
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
        
        return new StudentViewModel(studentRepository.save(student));
    }

    @Override
    public void deleteStudents(List<Integer> ids) {
        studentRepository.deleteAllById(ids);
    }
    
}
