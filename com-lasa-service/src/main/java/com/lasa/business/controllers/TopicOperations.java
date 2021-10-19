/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Topic;
import com.lasa.data.entity.utils.criteria.TopicSearchCriteria;
import com.lasa.data.entity.utils.page.TopicPage;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
public interface TopicOperations {

    @GetMapping
    ResponseEntity<?> findAll(TopicPage topicPage, TopicSearchCriteria searchCriteria);

    @GetMapping(value = "/{id}")
    Topic findById(
            @ApiParam(name = "id", type = "Integer", value = "By id, you may find a topic", required = true)
            @PathVariable Integer id);

    @PostMapping
    List<Topic> createTopics(
            @ApiParam(name = "id", type = "body", value = "Add a new topic", required = true)
            @RequestBody List<Topic> topics);

    @PutMapping
    List<Topic> updateTopics(
            @ApiParam(name = "topics", type = "body", value = "Update a topic", required = true)
            @RequestBody List<Topic> topics);

    @DeleteMapping
    void deleteTopics(
            @ApiParam(name = "ids", type = "body", value = "By id, you may remove a topic", required = true)
            @RequestBody List<Integer> ids);
}
