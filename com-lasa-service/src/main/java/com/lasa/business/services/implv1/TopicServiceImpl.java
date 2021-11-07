/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services.implv1;

import com.lasa.business.services.TopicService;
import com.lasa.data.model.entity.Topic;
import com.lasa.data.model.request.TopicRequestModel;
import com.lasa.data.model.utils.criteria.TopicSearchCriteria;
import com.lasa.data.model.utils.page.TopicPage;
import com.lasa.data.model.utils.specification.TopicSpecification;
import com.lasa.data.model.view.TopicViewModel;
import com.lasa.data.repo.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author hai
 */
@Component
@Qualifier("TopicServiceImplV1")
public class TopicServiceImpl implements TopicService {
    
    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Page<TopicViewModel> findWithArgument(TopicPage topicPage, TopicSearchCriteria searchCriteria) {
        Pageable pageable = PageRequest.of(topicPage.getPage(), topicPage.getSize(), Sort.by(topicPage.getOrderBy(), topicPage.getSortBy()));
        return topicRepository
                .findAll(TopicSpecification.searchSpecification(searchCriteria), pageable)
                .map(t -> new TopicViewModel(t));
    }

    @Override
    public List<TopicViewModel> findWithArgument(TopicSearchCriteria searchCriteria) {
        return topicRepository.findAll(TopicSpecification.searchSpecification(searchCriteria))
                .stream()
                .map(t -> new TopicViewModel(t))
                .collect(Collectors.toList());
    }

    @Override
    public List<TopicViewModel> createTopics(List<TopicRequestModel> topics) {
        List<Topic> topicList = topics.stream()
                .map(t -> t.toEntity())
                .collect(Collectors.toList());

        return topicRepository.saveAll(topicList)
                .stream()
                .map(t -> new TopicViewModel(t))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<TopicViewModel> updateTopics(List<TopicRequestModel> topics) {
        
        Set updateId = topics
                .stream()
                .filter(t -> t.getName() != null
                || t.getCourseId() != null
                || t.getDescription() != null
                || t.getStatus() != null
                || t.getMajorId() != null
                )
                .map(TopicRequestModel::getId)
                .collect(Collectors.toSet());
        
        List<Topic> topicList = topicRepository.findAllById(updateId);
        
        topicList
                .stream()
                .forEach(topic -> {
                    TopicRequestModel updateTopic = topics
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



        return topicRepository.saveAll(topicList)
                .stream()
                .map(t -> new TopicViewModel(t))
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteTopics(List<Integer> ids) {
        topicRepository.deleteAllById(ids);
    }
    
    @Override
    public TopicViewModel findById(Integer id) {
        return new TopicViewModel(topicRepository.findById(id).orElse(null));
    }
    
}
