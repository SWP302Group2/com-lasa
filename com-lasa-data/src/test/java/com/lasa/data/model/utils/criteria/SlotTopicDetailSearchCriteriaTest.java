/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.utils.criteria;

import com.lasa.data.model.entity.SlotTopicDetail;
import com.lasa.data.model.utils.specification.SlotTopicDetailSpecification;
import com.lasa.data.repo.repository.SlotTopicDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

;

/**
 *
 * @author MyNhung
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SlotTopicDetailSearchCriteriaTest {
    
    private final SlotTopicDetailRepository slotTopicDetailRepository;
    private final TestEntityManager entityManager;

    @Autowired
    public SlotTopicDetailSearchCriteriaTest(SlotTopicDetailRepository slotTopicDetailRepository,TestEntityManager entityManager) {
        this.slotTopicDetailRepository = slotTopicDetailRepository;
        this.entityManager = entityManager;
    }

    @Test
    public void testSlotTopicDetailSearchCriteriaWithSlotIdArgument() {
        List<Integer> searchSlotId = new ArrayList<>();
        searchSlotId.add(1);
        SlotTopicDetailSearchCriteria searchCriteria = SlotTopicDetailSearchCriteria.builder()
                .sId(searchSlotId)
                .build();
        List<SlotTopicDetail> actualList = slotTopicDetailRepository.findAll(SlotTopicDetailSpecification.searchSpecification(searchCriteria));

        List<SlotTopicDetail> expectedList = entityManager.getEntityManager()
                .createQuery("select s from SlotTopicDetail s where s.slot.id like ?1", SlotTopicDetail.class)
                .setParameter(1, searchSlotId)
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);

    }
     @Test
    public void testSlotTopicDetailSearchCriteriaWithSlotIdAndTopicIdArgument() {
        List<Integer> searchSlotId = new ArrayList<>();
        searchSlotId.add(1);
        List<Integer> searchTopicId = new ArrayList<>();
        searchTopicId.add(65);
        
        SlotTopicDetailSearchCriteria searchCriteria = new SlotTopicDetailSearchCriteria();
        searchCriteria.setSId(searchSlotId);
        searchCriteria.setTopicId(searchTopicId);   
        
        List<SlotTopicDetail> actualList = slotTopicDetailRepository.findAll(SlotTopicDetailSpecification.searchSpecification(searchCriteria));
        List<SlotTopicDetail> expectedList = entityManager.getEntityManager()
                .createQuery("select s from SlotTopicDetail s where s.slot.id in ?1 and s.topic.id in ?2", SlotTopicDetail.class)
                .setParameter(1, searchSlotId)
                .setParameter(2, searchTopicId)
                .getResultList();
        Assertions.assertEquals(expectedList, actualList);
    }

}
