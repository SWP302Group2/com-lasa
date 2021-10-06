/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.Slot;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author hai
 */
@Service
public interface SlotService {
    
    public List<Slot> findAll();
    
    public Slot findById(Integer id);
    
    public List<Slot> createSlots(List<Slot> slots);
    
    public List<Slot> updateSlots(List<Slot> slots);
    
    public void deleteSlots(List<Integer> ids);
}
