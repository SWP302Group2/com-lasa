/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.services.implv1.TopicServiceImpl;
import com.lasa.data.model.request.TopicRequestModel;
import com.lasa.data.model.view.TopicViewModel;
import com.lasa.data.repo.repository.TopicRepository;
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
public class TopicControllerTest {
    @Mock
    TopicRepository topicRepository;
    
    @Mock
    TopicServiceImpl topicService;

    @InjectMocks
   TopicController topicController;
    public TopicControllerTest() {
    }
    
    @Test
    public void testFindTopicById() throws Exception{
        int id = 1;
        TopicViewModel expected = new TopicViewModel();
        expected.setId(id);
        
        when(topicService.findById(1)).thenReturn(expected);
        ResponseEntity<?> result = topicController.findById(1);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
    @Test
    public void testCreateTopic(){
        TopicRequestModel topicRequest = new TopicRequestModel();
        topicRequest.setId(1);
        topicRequest.setMajorId("SE");
        
        List<TopicRequestModel> listTopic = new ArrayList<>();
        listTopic.add(topicRequest);
        
        TopicViewModel topic = new TopicViewModel();
        topic.setId(1);
        topic.setMajorId("SE");
        List<TopicViewModel> expected = new ArrayList<>();
        expected.add(topic);
        
        when(topicService.createTopics(listTopic)).thenReturn(expected);
        ResponseEntity<List<TopicViewModel>> result = topicController.createTopics(listTopic);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
     @Test
    public void testUpdateTopic(){      
        TopicViewModel topic = new TopicViewModel();
        topic.setId(1);
        topic.setMajorId("SS");
        List<TopicViewModel> expected = new ArrayList<>();
        expected.add(topic);
        
        TopicRequestModel topicRequest = new TopicRequestModel();
        topicRequest.setId(1);
        topicRequest.setMajorId("SE");        
        List<TopicRequestModel> listTopic = new ArrayList<>();
        listTopic.add(topicRequest);
        
        when(topicService.updateTopics(listTopic)).thenReturn(expected);
        ResponseEntity<List<TopicViewModel>> result = topicController.updateTopics(listTopic);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
}
