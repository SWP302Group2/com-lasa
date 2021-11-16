/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.model.request.LecturerRequestModel;
import com.lasa.data.model.utils.criteria.LecturerSearchCriteria;
import com.lasa.data.model.utils.page.LecturerPage;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

/**
 *
 * @author hai
 */
@RequestMapping("/default")
@Validated
public interface LecturerOperations {
    
    @GetMapping
    ResponseEntity<?> findWithArgument(
            LecturerPage lecturerPage,
            LecturerSearchCriteria searchCriteria
    );

    @GetMapping(value = "/{id}")
    ResponseEntity<?> findById(@PathVariable(value = "id") Integer id);

    @PostMapping
    ResponseEntity<?> createLecturer(
            @ApiParam(name = "lecturer", type = "body", value = "Create a new lecturer", required = true)
            @RequestBody LecturerRequestModel lecturer);
    
    @PatchMapping
    ResponseEntity<?> updateLecturer(@ApiParam(name = "lecturer", type = "body", value = "Update a lecturer", required = true)
                                     @Validated @RequestBody LecturerRequestModel model) throws ExceptionUtils.UpdateException, MessagingException;
    
    @DeleteMapping
    ResponseEntity<?> deleteLecturers(@ApiParam(name = "ids", type = "array", value = "Delete lecturer by id", required = true)
                                      @RequestParam List<Integer> id) throws ExceptionUtils.DeleteException;
    
}
