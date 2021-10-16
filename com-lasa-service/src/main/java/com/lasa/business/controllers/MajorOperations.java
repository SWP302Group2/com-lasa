/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Major;
import com.lasa.data.entity.utils.criteria.MajorSearchCriteria;
import com.lasa.data.entity.utils.page.MajorPage;
import io.swagger.annotations.ApiParam;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
public interface MajorOperations {

    @GetMapping(value = {"", "/topics", "/{id}"})
    ResponseEntity<?> findWithArgument(@ApiParam(name = "id", type = "string", value = "Get Major by id", required = false)
                                       @PathVariable(value = "id", required = false) String id,
                                       MajorPage majorPage,
                                       MajorSearchCriteria searchCriteria,
                                       HttpServletRequest request);

    @PostMapping
    void createMajors(
            @ApiParam(name = "majors", type = "body", value = "Add a major")
            @RequestBody List<Major> majors);

    @PutMapping
    List<Major> updateMajors(
            @ApiParam(name = "majors", type = "body", value = "Update a major")
            @RequestBody List<Major> majors);

    @DeleteMapping
    void deleteMajors(
            @ApiParam(name = "ids", type = "body", value = "Delete a major by id")
            @RequestBody List<String> ids);
}
