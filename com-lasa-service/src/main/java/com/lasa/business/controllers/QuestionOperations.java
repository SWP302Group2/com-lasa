/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Question;
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
 * @author hai
 */
@RequestMapping("/default")
public interface QuestionOperations {
    
    @GetMapping
    public List<Question> getAllQuestions();
    
    @GetMapping(value="/{id}")
    public Question getQuestionById(@PathVariable Integer id);
    
    @PostMapping
    public List<Question> createQuestions(@RequestBody List<Question> questions);
    
    @PutMapping
    public List<Question> updateQuestions(@RequestBody List<Question> questions);
    
    @DeleteMapping
    public void deleteQuestions(@RequestBody List<Integer> ids);
}
