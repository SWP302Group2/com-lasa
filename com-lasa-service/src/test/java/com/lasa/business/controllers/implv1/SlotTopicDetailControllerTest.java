/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.services.implv1.SlotTopicDetailServiceImpl;
import com.lasa.data.model.entity.key.SlotTopicDetailKey;
import com.lasa.data.model.request.SlotTopicDetailRequestModel;
import com.lasa.data.model.view.SlotTopicDetailViewModel;
import com.lasa.data.repo.repository.SlotTopicDetailRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author ASUS
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SlotTopicDetailControllerTest {
    
      @Mock
    SlotTopicDetailRepository slotTopicRepository;
    
    @Mock
   SlotTopicDetailServiceImpl slotTopicDetailService;

   @InjectMocks
   SlotTopicDetailController slotTopicController;
    public SlotTopicDetailControllerTest() {
    }

    @Test
    public void testFindSlotTopicDetailById() throws Exception {
        int id = 1;
        SlotTopicDetailKey slot = new SlotTopicDetailKey();
        slot.setSlotId(id);
        slot.setTopicId(id);
        
        SlotTopicDetailViewModel expected = new SlotTopicDetailViewModel();
        expected.setSlotId(id);
        expected.setTopicId(id);
        
        when(slotTopicDetailService.findById(slot)).thenReturn(expected);
        ResponseEntity<SlotTopicDetailViewModel> result = slotTopicController.findById(slot);
        Assertions.assertEquals(result.getBody(),expected);
    }

    @Test
    public void testCreateSlotTopicDetails() throws Exception{
        SlotTopicDetailViewModel detailView = new SlotTopicDetailViewModel(3, 1);
        List<SlotTopicDetailViewModel> expected = new ArrayList<>();
        expected.add(detailView);
        
        SlotTopicDetailRequestModel details = new SlotTopicDetailRequestModel(2, 1);
        List<SlotTopicDetailRequestModel> listDetail = new ArrayList<>();
        listDetail.add(details);
        
        when(slotTopicDetailService.createSlotTopicDetails(listDetail)).thenReturn(expected);
        ResponseEntity<List<SlotTopicDetailViewModel>> result = slotTopicController.createSlotTopicDetails(listDetail);
        Assertions.assertEquals(result.getBody(),expected);
    }
    
    @Test
    public void testUpdateSlotTopicDetails() throws Exception{
        SlotTopicDetailViewModel detailView = new SlotTopicDetailViewModel(3, 1);
        List<SlotTopicDetailViewModel> expected = new ArrayList<>();
        expected.add(detailView);
        
        SlotTopicDetailRequestModel details = new SlotTopicDetailRequestModel(2, 1);
        List<SlotTopicDetailRequestModel> listDetail = new ArrayList<>();
        listDetail.add(details);
        
        when(slotTopicDetailService.updateSlotTopicDetails(listDetail)).thenReturn(expected);
        ResponseEntity<List<SlotTopicDetailViewModel>> result = slotTopicController.updateSlotTopicDetails(listDetail);
        Assertions.assertEquals(result.getBody(),expected);
    }
   
    
}
