/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.model.request.StudentRequestModel;
import com.lasa.data.model.utils.criteria.StudentSearchCriteria;
import com.lasa.data.model.utils.page.StudentPage;
import com.lasa.security.utils.exception.ExceptionUtils;
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
    ResponseEntity<?> findByStudentId(
            @ApiParam(name = "id", type = "Integer", value = "By id, you may find a student", required = true)
            @PathVariable Integer id);

    @PostMapping
    ResponseEntity<?> createStudent(@ApiParam(name = "student", type = "body", value = "Create a new student", required = true)
                                    @RequestBody StudentRequestModel student);

    @PutMapping
    ResponseEntity<?> updateStudent(@ApiParam(name = "student", type = "body", value = "Update a student", required = true)
                                    @RequestBody StudentRequestModel student) throws ExceptionUtils.UpdateException;

    @DeleteMapping
    ResponseEntity<?> deleteStudents(@ApiParam(name = "id", type = "param", value = "Remove a student by id", required = true)
                        @RequestParam List<Integer> id) throws ExceptionUtils.DeleteException;
}
