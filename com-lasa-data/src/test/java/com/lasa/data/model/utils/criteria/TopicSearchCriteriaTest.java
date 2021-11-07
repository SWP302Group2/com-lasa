/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.utils.criteria;

import com.lasa.data.model.entity.Topic;
import com.lasa.data.model.utils.specification.TopicSpecification;
import com.lasa.data.repo.repository.TopicRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author MyNhung
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TopicSearchCriteriaTest {
    
    private final TopicRepository topicRepository;
    private final TestEntityManager entityManager;

    @Autowired
    public TopicSearchCriteriaTest(TopicRepository topicRepository,TestEntityManager entityManager) {
        this.topicRepository = topicRepository;
        this.entityManager = entityManager;
    }

    @Test
    public void testTopicSearchCriteriaWithNameArgument() {
        String searchName = "Programming";
        TopicSearchCriteria searchCriteria = TopicSearchCriteria.builder()
                .name(searchName)
                .build();
        List<Topic> actualList = topicRepository.findAll(TopicSpecification.searchSpecification(searchCriteria));

        List<Topic> expectedList = entityManager.getEntityManager()
                .createQuery("select t from Topic t where t.name like ?1", Topic.class)
                .setParameter(1, "%" + searchName + "%")
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    public void testTopicSearchCriteriaWithNameAndCourseIdArgument() {
        String searchName = "Programming";
        String searchCourseId = "PRF192";
        TopicSearchCriteria searchCriteria = TopicSearchCriteria.builder()
                 .name(searchName)
                 .courseId(searchCourseId)
                .build();

        List<Topic> actualList = topicRepository.findAll(TopicSpecification.searchSpecification(searchCriteria));

        List<Topic> expectedList = entityManager.getEntityManager()
                .createQuery("select s from Topic s where s.name like ?1 and s.courseId like ?2", Topic.class)
                .setParameter(1, "%" + searchName + "%")
                .setParameter(2, "%" + searchCourseId + "%")
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);
    }
     @Test
    public void testTopicSearchCriteriaWithMajorIdArgument() {
        String searchMajor = "SS";
        TopicSearchCriteria searchCriteria = TopicSearchCriteria.builder()
                 .name(searchMajor)
                .build();

        List<Topic> actualList = topicRepository.findAll(TopicSpecification.searchSpecification(searchCriteria));

        List<Topic> expectedList = entityManager.getEntityManager()
                .createQuery("select s from Topic s where s.majorId like ?1", Topic.class)
                .setParameter(1, "%" + searchMajor + "%")
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);
    }
    
}
