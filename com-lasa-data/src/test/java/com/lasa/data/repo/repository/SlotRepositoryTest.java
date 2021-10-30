/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.SlotTopicDetail;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 *
 * @author ASUS
 */
@DataJpaTest
public class SlotRepositoryTest {
    
    public SlotRepositoryTest() {
    }

     @Autowired
    SlotRepository slotRepository;
     
     @Test
    public void testCountSlotByTimeStartAndTimeEndAndLecturerIdIn(){
        LocalDateTime timeStart = LocalDateTime.parse("2021-10-08T09:43:32"); 
        LocalDateTime timeEnd = LocalDateTime.parse("2021-10-08T12:43:32");  
        long count = slotRepository.countSlotByTimeStartAndTimeEndAndLecturerIdIn(timeStart, timeEnd, 1);
        Assertions.assertEquals(count, 18);    
    }
    
    
}
