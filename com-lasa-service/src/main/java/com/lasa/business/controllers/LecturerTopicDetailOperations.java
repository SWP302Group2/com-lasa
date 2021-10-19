/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.LecturerTopicDetail;
import com.lasa.data.entity.key.LecturerTopicDetailKey;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author hai
 */
@RequestMapping("/default")
public interface LecturerTopicDetailOperations {
    
    @GetMapping
    public List<LecturerTopicDetail> findAll();
    
    @PostMapping
    public List<LecturerTopicDetail> createLecturerTopicDetails(
            @ApiParam(name = "lecturerTopicDetails", type = "body", value = "Add topic of lecturer", required = true)
            @RequestBody List<LecturerTopicDetail> lecturerTopicDetails);
    
    @DeleteMapping
    public void deleteLecturerTopicDetails(
            @ApiParam(name = "ids", type = "body", value = "Remove the lecturer's topic by id", required = true)
            @RequestBody List<LecturerTopicDetailKey> ids);
}
