/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.model.request.QuestionRequestModel;
import com.lasa.data.model.utils.criteria.QuestionSearchCriteria;
import com.lasa.data.model.utils.page.QuestionPage;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author hai
 */
@RequestMapping("/default")
public interface QuestionOperations {
    
    @GetMapping
    ResponseEntity<?> findWithArguments(QuestionPage questionPage, QuestionSearchCriteria searchCriteria);
    
    @GetMapping(value="/{id}")
    ResponseEntity<?> findById(
            @ApiParam(name = "ids", type = "Integer", value = "Get a question based on id.", required = true)
            @PathVariable Integer id);

    @PostMapping
    ResponseEntity<?> createQuestions(
            @ApiParam(name = "questions", type = "body", value = "Add a new question", required = true)
            @RequestBody List<QuestionRequestModel> questions);

    @PutMapping
    ResponseEntity<?> updateQuestions(
            @ApiParam(name = "questions", type = "body", value = "Update a question", required = true)
            @RequestBody List<QuestionRequestModel> questions);
    
    @DeleteMapping
    ResponseEntity<?> deleteQuestions(
            @ApiParam(name = "ids", type = "body", value = "Delete a question by id", required = true)
            @RequestBody List<Integer> ids);
}
