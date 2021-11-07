package com.lasa.business.services;

import com.lasa.business.services.implv1.TopicServiceImpl;
import com.lasa.data.model.entity.Topic;
import com.lasa.data.model.request.TopicRequestModel;
import com.lasa.data.model.view.SlotTopicDetailViewModel;
import com.lasa.data.model.view.TopicViewModel;
import com.lasa.data.repo.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;
    @InjectMocks
    private TopicServiceImpl topicService;

    private Topic topic;
    private List<TopicRequestModel> viewModels;
    private TopicRequestModel topicRequestModel;
    private List<Topic> topicList;
    @BeforeEach
    void setUp() {
        topicService = new TopicServiceImpl(topicRepository);

        topic = new Topic();
        topic.setId(1);

        topicRequestModel = new TopicRequestModel();
        topicRequestModel.setId(1);

        viewModels = new ArrayList<>();
        viewModels.add(topicRequestModel);

        topicList = new ArrayList<>();
        topicList.add(topicRequestModel.toEntity());

    }

    @Test
    void findWithArgument() {
    }

    @Test
    void testFindWithArgument() {
    }

    @Test
    void shouldFindById() {
        Optional<Topic> topicViewModelOptional = Optional.of(topic);
        when(topicRepository.findById(1))
                .thenReturn(topicViewModelOptional);
        TopicViewModel result = topicService.findById(1);
        assertEquals(1,result.getId());
    }

    @Test
    void shouldCreateTopics() {
        when(topicRepository.saveAll(topicList))
                .thenReturn(topicList);
        List<TopicViewModel> result =  topicService.createTopics(viewModels);
        assertEquals(1, result.get(0).getId());
    }

    @Test
    void updateTopics() {

    }

    @Test
    void shouldDeleteTopics() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        topicService.deleteTopics(ids);
        verify(topicRepository).deleteAllById(ids);
    }
}