/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.request;

import com.lasa.data.model.entity.Slot;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ASUS
 */
@SpringBootTest
public class SlotRequestModelTest {
    
   
    @Test
    public void toEntityTestGivenRightArgumentReturnWells() {
        SlotRequestModel model = new SlotRequestModel();
        Integer id = 1;
        Integer lecturerId = 1;
        LocalDateTime timeStart = LocalDateTime.now();
        LocalDateTime timeEnd = LocalDateTime.now().plusMinutes(30);

        model.setId(id);
        model.setLecturerId(lecturerId);
        model.setTimeStart(timeStart);
        model.setTimeEnd(timeEnd);
       
        Slot slot = model.toEntity();

        Assertions.assertEquals(slot.getId(), id);
        Assertions.assertEquals(slot.getLecturerId(), lecturerId);
        Assertions.assertEquals(slot.getTimeStart(), timeStart);
        Assertions.assertEquals(slot.getTimeEnd(), timeEnd);
    }
}
