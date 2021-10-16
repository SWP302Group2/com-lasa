/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.Slot;
import java.util.List;

import com.lasa.data.entity.utils.page.SlotPage;
import com.lasa.data.entity.utils.criteria.SlotSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author hai
 */
@Service
public interface SlotService {
    
    Page<Slot> findAll(SlotSearchCriteria searchCriteria, SlotPage slotPage);

    List<Slot> findAll(SlotSearchCriteria searchCriteria);

    Slot findById(Integer id);
    
    List<Slot> createSlots(List<Slot> slots);
    
    List<Slot> updateSlots(List<Slot> slots);
    
    void deleteSlots(List<Integer> ids);
}
