/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Student;
import java.util.List;

import com.lasa.data.entity.utils.StudentPage;
import com.lasa.data.entity.utils.StudentSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<Page<Student>> findAll(StudentSearchCriteria searchCriteria, StudentPage studentPage);

    @GetMapping(value = "/{id}")
    Student findByStudentId(@PathVariable Integer id);

    @PostMapping
    Student createStudent(@RequestBody Student student);

    @PutMapping
    Student updateStudent(@RequestBody Student student);

    @DeleteMapping
    void deleteStudents(@RequestBody List<Integer> ids);
}
