/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllersv1;

import com.lasa.business.controllers.StudentOperations;
import com.lasa.data.entity.Student;
import com.lasa.business.services.StudentService;
import java.util.List;

import com.lasa.data.entity.utils.page.StudentPage;
import com.lasa.data.entity.utils.criteria.StudentSearchCriteria;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/students")
@Api(value = "students", description = "About students", tags = { "Students Controller" })
public class StudentRestControllerV1 implements StudentOperations {

    private final StudentService service;

    @Autowired
    public StudentRestControllerV1(@Qualifier("StudentServiceImplV1") StudentService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Page<Student>> findAll(StudentSearchCriteria searchCriteria, StudentPage studentPage) {
        return ResponseEntity.ok(service.findAll(searchCriteria, studentPage));
    }

    @Override
    public Student findByStudentId(Integer id) {
        return service.findByStudentId(id);
    }

    @Override
    public Student createStudent(Student student) {
        return service.createStudent(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return service.updateStudent(student);
    }

    @Override
    public void deleteStudents(List<Integer> ids) {
        service.deleteStudents(ids);
    }

}
