/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Major;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
public interface MajorOperations {

    @GetMapping("/get")
    ResponseEntity<?> getMajors(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNum,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer pageSize
    );

    @GetMapping
    public List<Major> getAllMajor();

    @GetMapping(value = "/{id}")
    public Major getMajorById(@PathVariable String id);

    @PostMapping
    public void createMajors(@RequestBody List<Major> majors);

    @PutMapping
    public List<Major> updateMajors(@RequestBody List<Major> majors);

    @DeleteMapping
    public void deleteMajors(@RequestBody List<String> ids);
}
