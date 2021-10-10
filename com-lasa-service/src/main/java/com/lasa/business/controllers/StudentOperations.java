/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Student;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
public interface StudentOperations {

    @GetMapping
    public List<Student> findAll();

    @GetMapping(value = "/{id}")
    public Student findByStudentId(
            @ApiParam(name = "id", type = "integer", value = "By id, you may find a student", required = true)
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
