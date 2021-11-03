/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.model.request.QuestionRequestModel;
import com.lasa.data.model.view.QuestionViewModel;
import com.lasa.data.model.entity.Question;
import com.lasa.data.model.utils.criteria.QuestionSearchCriteria;
import com.lasa.data.model.utils.page.QuestionPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */
@Service
public interface QuestionService {
    
    Page<QuestionViewModel> findAll(QuestionPage questionPage, QuestionSearchCriteria searchCriteria);

    List<QuestionViewModel> findAll(QuestionSearchCriteria searchCriteria);
    
    QuestionViewModel findById(Integer id);

    boolean verifyAvailableQuestionForDelete(Integer bookingId, Integer studentId, List<Integer> questionIds);
    
    List<QuestionViewModel> createQuestions(List<QuestionRequestModel> questions);
    
    List<QuestionViewModel> updateQuestions(List<QuestionRequestModel> questions);
    
    void deleteQuestion(List<Integer> ids);
    
}
