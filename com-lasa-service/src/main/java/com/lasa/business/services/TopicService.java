/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.Topic;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author hai
 */
@Service
public interface TopicService {
    
    public List<Topic> findAll();
    
    public Topic findById(Integer id);
    
    public List<Topic> createTopics(List<Topic> topics);
    
    public List<Topic> updateTopics(List<Topic> topics);
    
    public void deleteTopics(List<Integer> ids);
    
}
