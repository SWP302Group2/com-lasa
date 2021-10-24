/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.QuestionOperations;
import com.lasa.business.services.QuestionService;
import com.lasa.data.model.view.QuestionViewModel;
import com.lasa.data.model.entity.Question;
import com.lasa.data.model.utils.criteria.QuestionSearchCriteria;
import com.lasa.data.model.utils.page.QuestionPage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author wifil
 */
@RestController
@RequestMapping("/api/v1/questions")
@Api(value = "questions", description = "About questions", tags = { "Questions Controller" })
public class QuestionController implements QuestionOperations {
    
    private final QuestionService questionService;

    @Autowired
    public QuestionController(@Qualifier("QuestionServiceImplV1") QuestionService service) {
        this.questionService = service;
    }
    
    @Override
    public ResponseEntity<?> findWithArguments(QuestionPage questionPage, QuestionSearchCriteria searchCriteria) {
        if(questionPage.isPaging())
            return ResponseEntity.ok(questionService.findAll(questionPage, searchCriteria));
        else
            return ResponseEntity.ok(questionService.findAll(searchCriteria));
    }
    
    @Override
    public ResponseEntity<QuestionViewModel> findById(@PathVariable Integer id) {
        QuestionViewModel question = questionService.findById(id);
        if(Objects.isNull(question))
            return ResponseEntity.ok(null);

        return ResponseEntity.ok(question);
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
