/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.data.repo.repository;

import com.lasa.data.model.entity.SlotTopicDetail;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 *
 * @author ASUS
 */
@DataJpaTest
public class SlotTopicDetailRepositoryTest {
    
    public SlotTopicDetailRepositoryTest() {
    }

     @Autowired
    SlotTopicDetailRepository slotDetailRepository;
     
     @Test
    public void testFindAllWithTopicBySlotId(){
        List<Integer> listId = new ArrayList<>();
        listId.add(1);
        int expectedTopicID = 65;
        List<SlotTopicDetail> slotTopicDetail = slotDetailRepository.findAllWithTopicBySlotId(listId);
        Assertions.assertEquals(slotTopicDetail.get(0).getTopic().getId(), expectedTopicID);             
    }
    
     @Test
    public void testFindAllSlotAndTopicBySlotId(){
        List<Integer> listId = new ArrayList<>();
        listId.add(1);
        int expectedSlotId = 1;
        int expectedTopicID = 65;
        List<SlotTopicDetail> slotTopicDetail = slotDetailRepository.findAllSlotAndTopicBySlotId(listId);
        Assertions.assertEquals(slotTopicDetail.get(0).getSlot().getId(), expectedSlotId);
        Assertions.assertEquals(slotTopicDetail.get(0).getTopic().getId(), expectedTopicID);             
    }
    
    @Test
    public void testFindAllSlotAndTopicByTopicId(){
        List<Integer> listId = new ArrayList<>();
        listId.add(65);
        int expectedTopicID = 65;
        int expectedSlotId = 1;
        List<SlotTopicDetail> slotTopicDetail = slotDetailRepository.findAllSlotAndTopicByTopicId(listId);
        Assertions.assertEquals(slotTopicDetail.get(0).getSlot().getId(), expectedSlotId);
        Assertions.assertEquals(slotTopicDetail.get(0).getTopic().getId(), expectedTopicID);             
    }
    
     @Test
    public void testFindAllSlotAndTopicByTopicIdAndSlotId(){
        List<Integer> listSlotId = new ArrayList<>();
        listSlotId.add(1);
        List<Integer> listTopicId = new ArrayList<>();
        listTopicId.add(65);
        
        int expectedTopicID = 65;
        int expectedSlotId = 1;
        List<SlotTopicDetail> slotTopicDetail = slotDetailRepository.findAllSlotAndTopicByTopicIdAndSlotId(listSlotId, listTopicId);
        Assertions.assertEquals(slotTopicDetail.get(0).getSlot().getId(), expectedSlotId);
        Assertions.assertEquals(slotTopicDetail.get(0).getTopic().getId(), expectedTopicID);             
    }
    
    @Test
    public void testFindAllSlotAndTopic(){
        int expectedSlotId = 1;
        int expectedTopicID = 65;
        List<SlotTopicDetail> slotDetail = slotDetailRepository.findAllSlotAndTopic();
        Assertions.assertEquals(slotDetail.get(0).getSlot().getId(), expectedSlotId);
        Assertions.assertEquals(slotDetail.get(0).getTopic().getId(), expectedTopicID);          
    }
    
}
