/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.services.implv1.QuestionServiceImpl;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Question;
import com.lasa.data.model.request.QuestionRequestModel;
import com.lasa.data.model.view.QuestionViewModel;
import com.lasa.data.repo.repository.QuestionRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author ASUS
 */
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {
    
    public QuestionControllerTest() {
    }

    @Mock
    QuestionRepository questionRepository;
    
    @Mock
   QuestionServiceImpl questionService;

   @InjectMocks
   QuestionController questionController;
    @Test
    public void testFindWithArguments() {
    }

    @Test
    public void testFindQuestionById() throws Exception {
        int id = 1;
        QuestionViewModel expected = new QuestionViewModel();
        expected.setId(id);
        when(questionService.findById(id)).thenReturn(expected);
        ResponseEntity<QuestionViewModel> result = questionController.findById(id);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
     @Test
    public void testCreateQuestion() throws Exception { 
        BookingRequest request = new BookingRequest();
        request.setId(1);
        Question questions = new Question();
        questions.setId(1);
        questions.setBookingRequest(request);
        
        questions.setContent("Are you marry");
        QuestionViewModel questionView = new QuestionViewModel(questions);
        List<QuestionViewModel> expected = new ArrayList<>();
        expected.add(questionView);
        
        QuestionRequestModel questionModel = new QuestionRequestModel();
        questionModel.setId(1);
        questionModel.setContent("Are you marry");
        questionModel.setBookingId(1);
        List<QuestionRequestModel> question = new ArrayList<>();
        question.add(questionModel);
        
        when(questionService.createQuestions(question)).thenReturn(expected);
        ResponseEntity<List<QuestionViewModel>> result = questionController.createQuestions(question);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
     @Test
    public void testUpdateQuestion() throws Exception { 
        BookingRequest request = new BookingRequest();
        request.setId(1);
        Question questions = new Question();
        questions.setId(1);
        questions.setBookingRequest(request);
        
        questions.setContent("How old are you");
        QuestionViewModel questionView = new QuestionViewModel(questions);
        List<QuestionViewModel> expected = new ArrayList<>();
        expected.add(questionView);
        
        QuestionRequestModel questionModel = new QuestionRequestModel();
        questionModel.setId(1);
        questionModel.setContent("How old are you");
        questionModel.setBookingId(2);
        List<QuestionRequestModel> question = new ArrayList<>();
        question.add(questionModel);
        
        when(questionService.updateQuestions(question)).thenReturn(expected);
        ResponseEntity<List<QuestionViewModel>> result = questionController.updateQuestions(question);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
}
