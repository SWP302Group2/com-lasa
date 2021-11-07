/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 *
 * @author ASUS
 */
@DataJpaTest
public class SlotRepositoryTest {
    
    public SlotRepositoryTest() {
    }

    @Resource
    SlotRepository slotRepository;
     
    @Test
    public void testCountSlotByTimeStartAndTimeEndAndLecturerIdIn(){
        
        LocalDateTime timeStart = LocalDateTime.parse("2021-10-08T09:43:32"); 
        LocalDateTime timeEnd = LocalDateTime.parse("2021-10-08T12:43:32");  
        long count = slotRepository.countActiveSlotByTimeStartAndTimeEndAndLecturerId(timeStart, timeEnd, 1);
        Assertions.assertEquals(count, 0);
    }
    
    
}
