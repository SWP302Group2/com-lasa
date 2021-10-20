/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.dto.TopicDTO;
import com.lasa.data.entity.Topic;
import com.lasa.data.entity.utils.criteria.TopicSearchCriteria;
import com.lasa.data.entity.utils.page.TopicPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */
@Service
public interface TopicService {
    
    Page<TopicDTO> findWithArgument(TopicPage topicPage, TopicSearchCriteria searchCriteria);

    List<TopicDTO> findWithArgument(TopicSearchCriteria searchCriteria);
    
    TopicDTO findById(Integer id);
    
    List<Topic> createTopics(List<Topic> topics);
    
    List<Topic> updateTopics(List<Topic> topics);
    
    void deleteTopics(List<Integer> ids);
    
}
