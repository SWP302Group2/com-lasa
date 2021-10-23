/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Student;
import com.lasa.data.entity.utils.criteria.StudentSearchCriteria;
import com.lasa.data.entity.utils.page.StudentPage;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
public interface StudentOperations {

    @GetMapping
    ResponseEntity<?> findWithArguments(StudentSearchCriteria searchCriteria, StudentPage studentPage);

    @GetMapping(value = "/{id}")
    public Student findByStudentId(
            @ApiParam(name = "id", type = "Integer", value = "By id, you may find a student", required = true)
            @PathVariable Integer id);

    @PostMapping
    public Student createStudent(
            @ApiParam(name = "student", type = "body", value = "Create a new student", required = true)
            @RequestBody Student student);

    @PutMapping
    public Student updateStudent(
             @ApiParam(name = "student", type = "body", value = "Update a student", required = true)
            @RequestBody Student student);

    @DeleteMapping
    public void deleteStudents(
             @ApiParam(name = "ids", type = "body", value = "Remove a student by id", required = true)
            @RequestBody List<Integer> ids);
}
