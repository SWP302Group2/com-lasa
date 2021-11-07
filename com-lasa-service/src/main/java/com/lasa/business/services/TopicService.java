/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.model.request.TopicRequestModel;
import com.lasa.data.model.utils.criteria.TopicSearchCriteria;
import com.lasa.data.model.utils.page.TopicPage;
import com.lasa.data.model.view.TopicViewModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */
@Service
public interface TopicService {
    
    Page<TopicViewModel> findWithArgument(TopicPage topicPage, TopicSearchCriteria searchCriteria);

    List<TopicViewModel> findWithArgument(TopicSearchCriteria searchCriteria);
    
    TopicViewModel findById(Integer id);
    
    List<TopicViewModel> createTopics(List<TopicRequestModel> topics);
    
    List<TopicViewModel> updateTopics(List<TopicRequestModel> topics);
    
    void deleteTopics(List<Integer> ids);
    
}
