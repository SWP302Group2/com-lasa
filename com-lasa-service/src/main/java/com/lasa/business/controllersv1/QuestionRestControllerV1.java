/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllersv1;

import com.lasa.business.controllers.QuestionOperations;
import com.lasa.data.entity.Question;
import com.lasa.business.services.QuestionService;
import java.util.List;

import com.lasa.data.entity.utils.criteria.QuestionSearchCriteria;
import com.lasa.data.entity.utils.page.QuestionPage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author wifil
 */
@RestController
@RequestMapping("/api/v1/questions")
@Api(value = "questions", description = "About questions", tags = { "Questions Controller" })
public class QuestionRestControllerV1 implements QuestionOperations {
    
    private final QuestionService questionService;
    
    @Autowired
    public QuestionRestControllerV1(@Qualifier("QuestionServiceImplV1") QuestionService service) {
        this.questionService = service;
    }
    
    @Override
    public ResponseEntity<?> findAll(QuestionPage questionPage, QuestionSearchCriteria searchCriteria) {
        return ResponseEntity.ok(questionService.findAll(questionPage, searchCriteria));
    }
    
    @Override
    public Question findById(@PathVariable Integer id) {
        return questionService.findById(id);
    }
    
    @Override
    public List<Question> createQuestions(@RequestBody List<Question> questions) {
        return questionService.createQuestions(questions);
    }
    
    @Override
    public List<Question> updateQuestions(@RequestBody List<Question> questions) {
        return questionService.updateQuestions(questions);
    }
    
    @Override
    public void deleteQuestions(@RequestBody List<Integer> ids) {
        questionService.deleteQuestion(ids);
    }
    
}
