/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Slot;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
public interface SlotOperations {

//    @RequestMapping(value = "/status")
//    ResponseEntity<?> getSlotsByStatus(
//            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
//            @RequestParam(value = "size", required = false, defaultValue = "10") Integer pageSize,
//            @RequestParam(value = "value", required = true) Integer status
//    );

    @GetMapping
    public List<Slot> findAll();

    @GetMapping(value = "/{id}")
    public Slot findById(@PathVariable Integer id);

    @PostMapping
    public List<Slot> createSlots(@RequestBody List<Slot> slots);

    @PutMapping
    public List<Slot> updateSlots(@RequestBody List<Slot> slots);

    @DeleteMapping
    public void deleteSlots(@RequestBody List<Integer> ids);
}
