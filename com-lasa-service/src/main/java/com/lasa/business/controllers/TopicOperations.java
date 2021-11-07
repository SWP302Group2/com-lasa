/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.model.entity.Topic;
import com.lasa.data.model.request.TopicRequestModel;
import com.lasa.data.model.utils.criteria.TopicSearchCriteria;
import com.lasa.data.model.utils.page.TopicPage;
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
    ResponseEntity<?> findWithArguments(TopicPage topicPage, TopicSearchCriteria searchCriteria);

    @GetMapping(value = "/{id}")
    ResponseEntity<?> findById(@ApiParam(name = "id", type = "Integer", value = "Find topic by id", required = true)
                               @PathVariable Integer id);

    @PostMapping
    ResponseEntity<?> createTopics(
            @ApiParam(name = "id", type = "body", value = "Add a new topic", required = true)
            @RequestBody List<TopicRequestModel> topics);

    @PutMapping
    ResponseEntity<?> updateTopics(
            @ApiParam(name = "topics", type = "body", value = "Update a topic", required = true)
            @RequestBody List<TopicRequestModel> topics);

    @DeleteMapping
    ResponseEntity<?> deleteTopics(
            @ApiParam(name = "ids", type = "body", value = "By id, you may remove a topic", required = true)
            @RequestBody List<Integer> ids);
}
