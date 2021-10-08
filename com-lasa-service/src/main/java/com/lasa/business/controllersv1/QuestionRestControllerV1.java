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

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.*;

/**
 *
 * @author wifil
 */
@RestController
@RequestMapping("/api/v1/questions")
@Api(value = "questions", description = "About questions", tags = { "Questions Controller" })
public class QuestionRestControllerV1 implements QuestionOperations {
    
    private final QuestionService service;
    
    @Autowired
    public QuestionRestControllerV1(@Qualifier("QuestionServiceImplV1") QuestionService service) {
        this.service = service;
    }
    
    @Override
    public List<Question> getAllQuestions() {
        return service.findAll();
    }
    
    @Override
    public Question getQuestionById(@PathVariable Integer id) {
        return service.findById(id);
    }
    
    @Override
    public List<Question> createQuestions(@RequestBody List<Question> questions) {
        return service.createQuestions(questions);
    }
    
    @Override
    public List<Question> updateQuestions(@RequestBody List<Question> questions) {
        return service.updateQuestions(questions);
    }
    
    @Override
    public void deleteQuestions(@RequestBody List<Integer> ids) {
        service.deleteQuestion(ids);
    }
    
}
