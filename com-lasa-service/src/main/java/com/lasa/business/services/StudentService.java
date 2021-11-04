/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.model.request.StudentRequestModel;
import com.lasa.data.model.utils.criteria.StudentSearchCriteria;
import com.lasa.data.model.utils.page.StudentPage;
import com.lasa.data.model.view.StudentViewModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */
@Service
public interface StudentService {
    
    Page<StudentViewModel> findWithArgument(StudentSearchCriteria searchCriteria, StudentPage studentPage);

    List<StudentViewModel> findWithArgument(StudentSearchCriteria searchCriteria);
    
    StudentViewModel findByStudentId(Integer id);
    
    StudentViewModel createStudent(StudentRequestModel student);
    
    StudentViewModel updateStudent(StudentRequestModel student);
    
    void deleteStudents(List<Integer> ids);
}
