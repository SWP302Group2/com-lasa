/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.utils.criteria;

import com.lasa.data.model.entity.Question;
import com.lasa.data.model.utils.specification.QuestionSpecification;
import com.lasa.data.repo.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MyNhung
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class QuestionSearchCriteriaTest {
    
    private final QuestionRepository questionRepository;
    private final TestEntityManager entityManager;

    @Autowired
    public QuestionSearchCriteriaTest(QuestionRepository questionRepository,TestEntityManager entityManager) {
        this.questionRepository = questionRepository;
        this.entityManager = entityManager;
    }

    @Test
    public void testQuestionSearchCriteriaWithBookingIdArgument() {
        List<Integer> searchBookingId = new ArrayList<>();
        searchBookingId.add(1);
        QuestionSearchCriteria searchCriteria = QuestionSearchCriteria.builder()
                .bookingId(searchBookingId)
                .build();
        List<Question> actualList = questionRepository.findAll(QuestionSpecification.searchSpecification(searchCriteria));

        List<Question> expectedList = entityManager.getEntityManager()
                .createQuery("select q from Question q where q.bookingRequest.id in ?1", Question.class)
                .setParameter(1, searchBookingId)
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);

    }
    
}
