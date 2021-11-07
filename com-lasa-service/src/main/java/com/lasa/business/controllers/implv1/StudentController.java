/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.StudentOperations;
import com.lasa.business.services.StudentService;
import com.lasa.data.model.request.StudentRequestModel;
import com.lasa.data.model.utils.criteria.StudentSearchCriteria;
import com.lasa.data.model.utils.page.StudentPage;
import com.lasa.data.model.view.StudentViewModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/students")
@Api(value = "students", description = "About students", tags = { "Students Controller" })
public class StudentController implements StudentOperations {

    private final StudentService service;

    @Autowired
    public StudentController(@Qualifier("StudentServiceImplV1") StudentService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> findWithArguments(StudentSearchCriteria searchCriteria, StudentPage studentPage) {
        if(studentPage.isPaging())
            return ResponseEntity.ok(service.findWithArgument(searchCriteria, studentPage));
        else
            return ResponseEntity.ok(service.findWithArgument(searchCriteria));
    }

    @Override
    public ResponseEntity<StudentViewModel> findByStudentId(Integer id) {
        return ResponseEntity.ok(service.findByStudentId(id));
    }

    @Override
    public ResponseEntity<StudentViewModel> createStudent(StudentRequestModel student) {
        return ResponseEntity.ok(service.createStudent(student));
    }

    @Override
    public ResponseEntity<StudentViewModel> updateStudent(StudentRequestModel student) {
        return ResponseEntity.ok(service.updateStudent(student));
    }

    @Override
    public void deleteStudents(List<Integer> ids) {
        /*service.deleteStudents(ids);*/
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
