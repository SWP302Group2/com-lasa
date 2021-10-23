/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Lecturer;
import com.lasa.data.entity.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.entity.utils.page.LecturerPage;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author hai
 */
@RequestMapping("/default")
public interface LecturerOperations {
    
    @GetMapping
    ResponseEntity<?> findWithArgument(
            LecturerPage lecturerPage,
            LecturerSearchCriteria searchCriteria
    );

    @GetMapping(value = "/{id}")
    ResponseEntity<?> findById(@PathVariable(value = "id") Integer id);

    @PostMapping
    Lecturer createLecturer(
            @ApiParam(name = "lecturer", type = "body", value = "Create a new lecturer", required = true)
            @RequestBody Lecturer lecturer);
    
    @PutMapping
    Lecturer updateLecturer(
            @ApiParam(name = "lecturer", type = "body", value = "Update a lecturer", required = true)
            @RequestBody Lecturer lecturer);
    
    @DeleteMapping
    void deleteLecturers(
            @ApiParam(name = "ids", type = "array", value = "Delete lecturer by id", required = true)
            List<Integer> ids);
    
}
