/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.Question;
import java.util.List;

import com.lasa.data.entity.utils.criteria.QuestionSearchCriteria;
import com.lasa.data.entity.utils.page.QuestionPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author hai
 */
@Service
public interface QuestionService {
    
    Page<Question> findAll(QuestionPage questionPage, QuestionSearchCriteria searchCriteria);
    
    public Question findById(Integer id);
    
    public List<Question> createQuestions(List<Question> questions);
    
    public List<Question> updateQuestions(List<Question> questions);
    
    public void deleteQuestion(List<Integer> ids);
    
}
