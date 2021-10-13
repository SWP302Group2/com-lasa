/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Major;
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
public interface MajorOperations {

    @GetMapping
    public List<Major> getAllMajor();

    @GetMapping(value = "/{id}")
    public Major getMajorById(
            @ApiParam(name = "id", type = "string", value = "Get Major by id", required = true)
            @PathVariable String id);

    @PostMapping
    public void createMajors(
            @ApiParam(name = "majors", type = "body", value = "Add a major")
            @RequestBody List<Major> majors);

    @PutMapping
    public List<Major> updateMajors(
            @ApiParam(name = "majors", type = "body", value = "Update a major")
            @RequestBody List<Major> majors);

    @DeleteMapping
    public void deleteMajors(
            @ApiParam(name = "ids", type = "body", value = "Delete a major by id")
            @RequestBody List<String> ids);
}
