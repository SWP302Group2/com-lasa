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

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.*;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin(
        allowCredentials = "true",
        origins = {"http://localhost:3000", "http://localhost:5500", "https://lasa-fpt.web.app"},
        allowedHeaders = {
            CONTENT_TYPE,
            CONTENT_LENGTH,
            HOST,
            USER_AGENT,
            ACCEPT,
            ACCEPT_ENCODING,
            CONNECTION,
            AUTHORIZATION
        },
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS}
)
@Api(value = "students", description = "About students", tags = {"Students Controller"})
public class StudentRestControllerV1 implements StudentOperations {

    private final StudentService service;

    @Autowired
    public StudentRestControllerV1(@Qualifier("StudentServiceImplV1") StudentService service) {
        this.service = service;
    }

    @Override
    public List<Student> findAll() {
        return service.findAll();
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

    @Override
    public ResponseEntity<?> findBasicInformationStudents(Integer page, Integer size) {
        return ResponseEntity.ok(service.findBasicInformationLecturer(page, size));
    }

}
