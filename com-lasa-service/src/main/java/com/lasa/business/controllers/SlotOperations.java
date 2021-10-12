/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Slot;
import java.util.List;

import com.lasa.data.entity.utils.SlotPage;
import com.lasa.data.entity.utils.SlotSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
public interface SlotOperations {

    @GetMapping
    ResponseEntity<Page<Slot>> findAll(SlotSearchCriteria searchCriteria, SlotPage slotPage);

    @GetMapping(value = "/{id}")
    public Slot findById(@PathVariable Integer id);

    @PostMapping
    public List<Slot> createSlots(@RequestBody List<Slot> slots);

    @PutMapping
    public List<Slot> updateSlots(@RequestBody List<Slot> slots);

    @DeleteMapping
    public void deleteSlots(@RequestBody List<Integer> ids);
}
