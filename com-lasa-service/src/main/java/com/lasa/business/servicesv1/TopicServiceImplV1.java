/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.servicesv1;

import com.lasa.data.entity.Topic;
import com.lasa.data.repository.TopicRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.lasa.business.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hai
 */
@Component
@Qualifier("TopicServiceImplV1")
public class TopicServiceImplV1 implements TopicService {
    
    @Autowired
    TopicRepository topicRepository;
    
    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }
    
    @Override
    public List<Topic> createTopics(List<Topic> topics) {
        return topicRepository.saveAll(topics);
    }
    
    @Override
    @Transactional
    public List<Topic> updateTopics(List<Topic> topics) {
        
        Set updateId = topics
                .stream()
                .filter(t -> t.getName() != null
                || t.getCourseId() != null
                || t.getDescription() != null
                || t.getStatus() != null
                || t.getMajorId() != null
                )
                .map(Topic::getId)
                .collect(Collectors.toSet());
        
        List<Topic> topicList = (List<Topic>) topicRepository
                .findAllById(updateId)
                .stream()
                .collect(Collectors.toList());
        
        topicList
                .stream()
                .forEach(topic -> {
                    Topic updateTopic = topics
                            .stream()
                            .filter(t -> t.getId().equals(topic.getId()))
                            .findAny()
                            .get();
                    
                    if (updateTopic.getName() != null) {
                        topic.setName(updateTopic.getName());
                    }
                    
                    if (updateTopic.getCourseId() != null) {
                        topic.setCourseId(updateTopic.getCourseId());
                    }
                    
                    if (updateTopic.getDescription() != null) {
                        topic.setDescription(updateTopic.getDescription());
                    }
                    
                    if (updateTopic.getMajorId() != null) {
                        topic.setMajorId(updateTopic.getMajorId());
                    }
                    
                    if (updateTopic.getStatus() != null) {
                        topic.setStatus(updateTopic.getStatus());
                    }
                });
        
        return topicRepository.saveAll(topics);
    }
    
    @Override
    public void deleteTopics(List<Integer> ids) {
        topicRepository.deleteAllById(ids);
    }
    
    @Override
    public Topic findById(Integer id) {
        return topicRepository.findById(id).orElse(null);
    }
    
}
