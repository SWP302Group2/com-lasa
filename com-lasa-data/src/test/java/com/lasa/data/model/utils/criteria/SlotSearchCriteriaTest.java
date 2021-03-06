/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.utils.criteria;

import com.lasa.data.model.entity.Slot;
import com.lasa.data.model.utils.specification.SlotSpecification;
import com.lasa.data.repo.repository.SlotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author ASUS
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SlotSearchCriteriaTest {
    
    private final SlotRepository slotRepository;
    private final TestEntityManager entityManager;

    @Autowired
    public SlotSearchCriteriaTest(SlotRepository slotRepository,TestEntityManager entityManager) {
        this.slotRepository = slotRepository;
        this.entityManager = entityManager;
    }

    @Test
    public void testSearchTimeStartEndWithArgument() {
        LocalDateTime timeStart = LocalDateTime.parse("2021-10-08T09:43:32"); 
        LocalDateTime timeEnd = LocalDateTime.parse("2021-10-08T12:43:32");  
        SlotSearchCriteria searchCriteria = SlotSearchCriteria.builder()
                .timeStart(timeStart)
                .timeEnd(timeEnd)
                .build();
        List<Slot> actualList = slotRepository.findAll(SlotSpecification.searchSpecification(searchCriteria));
        List<Slot> expectedList = entityManager.getEntityManager()
                .createQuery("select slot from Slot slot where slot.timeStart <= ?2 and slot.timeStart >= ?1 " +
                        "and slot.timeEnd <= ?2 and slot.timeEnd >= ?1", Slot.class)
                .setParameter(1,   timeStart)
                .setParameter(2,   timeEnd )
                .getResultList();

        Assertions.assertEquals(expectedList, actualList);
    }

    
}
