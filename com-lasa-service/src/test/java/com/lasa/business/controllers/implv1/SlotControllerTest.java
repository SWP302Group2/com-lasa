/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.services.implv1.SlotServiceImpl;
import com.lasa.data.model.entity.Slot;
import com.lasa.data.model.request.SlotBookingRequestModel;
import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.model.view.SlotViewModel;
import com.lasa.data.repo.repository.SlotRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

/**
 *
 * @author ASUS
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SlotControllerTest {
    
     @Mock
    SlotRepository slotRepository;
    
    @Mock
   SlotServiceImpl slotService;

   @InjectMocks
   SlotController slotController;
    public SlotControllerTest() {
    }

     @Test
    public void testFindSlotById() throws Exception {
        int id = 1;
        SlotViewModel expected = new SlotViewModel();
        expected.setId(id);
        when(slotService.findById(id)).thenReturn(expected);
        ResponseEntity<SlotViewModel> result = slotController.findById(id);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
 /*   @Test
    public void testCreateQuestion() throws Exception { 
        Slot slots = new Slot();
        slots.setId(1);
        slots.setLecturerId(2);
        slots.setStatus(1);
        
        slots.setTopics(topics);
        SlotViewModel expected = new SlotViewModel(slots);
        
        SlotRequestModel slot = new SlotRequestModel();
        slot.setId(1);
        slot.setLecturerId(2);
        slot.setStatus(1);
        List<Integer> topics = new ArrayList<>();
        topics.add(1);
        slot.setTopics(topics);
             
        when(slotService.createSlot(slot)).thenReturn(expected);
        ResponseEntity<SlotViewModel> result = slotController.createSlot(slot);
        Assertions.assertEquals(result.getBody(),expected);
    }*/
    
    
}
