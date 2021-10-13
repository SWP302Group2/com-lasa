/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Topic;
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
public interface TopicOperations {

    @GetMapping
    public List<Topic> findAll();

    @GetMapping(value = "/{id}")
    public Topic findById(
            @ApiParam(name = "id", type = "integer", value = "By id, you may find a topic", required = true)
            @PathVariable Integer id);

    @PostMapping
    public List<Topic> createTopics(
            @ApiParam(name = "id", type = "body", value = "Add a new topic", required = true)
            @RequestBody List<Topic> topics);

    @PutMapping
    public List<Topic> updateTopics(
            @ApiParam(name = "topics", type = "body", value = "Update a topic", required = true)
            @RequestBody List<Topic> topics);

    @DeleteMapping
    public void deleteTopics(
            @ApiParam(name = "ids", type = "body", value = "By id, you may remove a topic", required = true)
            @RequestBody List<Integer> ids);
}
