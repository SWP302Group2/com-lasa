/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.TopicOperations;
import com.lasa.business.services.TopicService;
import com.lasa.data.model.request.TopicRequestModel;
import com.lasa.data.model.utils.criteria.TopicSearchCriteria;
import com.lasa.data.model.utils.page.TopicPage;
import com.lasa.data.model.view.TopicViewModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api/v1/topics")
@Api(value = "topics", description = "About topics", tags = { "Topics Controller" })
public class TopicController implements TopicOperations {
    
    private final TopicService topicService;
    
    @Autowired
    public TopicController(@Qualifier("TopicServiceImplV1") TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    public ResponseEntity<?> findWithArguments(TopicPage topicPage, TopicSearchCriteria searchCriteria) {
        if(topicPage.isPaging())
            return ResponseEntity.ok(topicService.findWithArgument(topicPage, searchCriteria));
        else
            return ResponseEntity.ok(topicService.findWithArgument(searchCriteria));
    }
     
    @Override
    public ResponseEntity<List<TopicViewModel>> createTopics(List<TopicRequestModel> topics) {
        return ResponseEntity.ok(topicService.createTopics(topics));
    }
    
    @Override
    public ResponseEntity<List<TopicViewModel>> updateTopics(List<TopicRequestModel> topics) {
        return ResponseEntity.ok(topicService.updateTopics(topics));
    }

    @Override
    public ResponseEntity<?> findById(Integer id) {
        return ResponseEntity.ok(topicService.findById(id));
    }

    @Override
    public ResponseEntity deleteTopics(List<Integer> ids) {
        topicService.deleteTopics(ids);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    
    
    
   
}
