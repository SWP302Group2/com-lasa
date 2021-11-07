/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.model.request;

import com.lasa.data.model.entity.SlotTopicDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ASUS
 */
@SpringBootTest
public class SlotTopicDetailRequestModelTest {
    
     @Test
    public void toEntityTestGivenRightArgumentReturnWells() {
        SlotTopicDetailRequestModel model = new SlotTopicDetailRequestModel();
        Integer slotId = 1;
        Integer topicId = 1;

        model.setSlotId(slotId);
        model.setTopicId(topicId);    
        
        SlotTopicDetail slotTopicDetail = model.toEntity();

        Assertions.assertEquals(slotTopicDetail.getSlot().getId() ,slotId);
        Assertions.assertEquals(slotTopicDetail.getTopic().getId(), topicId);
    }
    
}
