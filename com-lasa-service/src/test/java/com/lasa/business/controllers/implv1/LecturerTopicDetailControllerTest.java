/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.services.implv1.LecturerTopicDetailServiceImpl;
import com.lasa.data.model.entity.Lecturer;
import com.lasa.data.model.entity.LecturerTopicDetail;
import com.lasa.data.model.entity.Topic;
import com.lasa.data.repo.repository.LecturerTopicDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 *
 * @author ASUS
 */
@SpringBootTest
@AutoConfigureMockMvc
public class LecturerTopicDetailControllerTest {
    
    @Autowired
    private MockMvc mocMvc;
   
   @Mock
   LecturerTopicDetailRepository lecturerTopicRepository;
    
   @Mock
   LecturerTopicDetailServiceImpl lecturerTopicService;

   @InjectMocks
   LecturerTopicDetailController lecturerTopicController;
    public LecturerTopicDetailControllerTest() {
    }

    @Test
    public void testFindAll() {
    }
     @Test
    public void testCreateLecturerTopicDetail() throws Exception {        
        Lecturer lecturer = new Lecturer();
        lecturer.setId(3);
        Topic topic = new Topic();
        topic.setId(1);
        
        LecturerTopicDetail details = new LecturerTopicDetail(lecturer, topic);
        List<LecturerTopicDetail> listDetail = new ArrayList<>();
        listDetail.add(details);
        
        LecturerTopicDetail expectedModel = new LecturerTopicDetail(lecturer, topic);
        List<LecturerTopicDetail> expected = new ArrayList<>();
        expected.add(expectedModel);
        
        when(lecturerTopicService.createLecturerTopicDetails(listDetail)).thenReturn(expected);
        ResponseEntity<?> result = lecturerTopicController.createLecturerTopicDetails(listDetail);
        Assertions.assertEquals(result.getBody(), expected);
     
     
    }
   
    
}
