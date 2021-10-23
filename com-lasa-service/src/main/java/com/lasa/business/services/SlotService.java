/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.dto.SlotDTO;
import com.lasa.data.entity.Slot;
import com.lasa.data.entity.utils.criteria.SlotSearchCriteria;
import com.lasa.data.entity.utils.page.SlotPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author hai
 */
@Service
public interface SlotService {
    
    Page<SlotDTO> findWithArguments(SlotSearchCriteria searchCriteria, SlotPage slotPage);

    List<SlotDTO> findWithArguments(SlotSearchCriteria searchCriteria);

    SlotDTO findById(Integer id);

    Boolean verifySlot(Slot slot);

    Slot createSlot(Slot slot);

    List<Slot> createSlots(List<Slot> slots);
    
    List<Slot> updateSlots(List<Slot> slots);
    
    void deleteSlots(List<Integer> ids);
}
