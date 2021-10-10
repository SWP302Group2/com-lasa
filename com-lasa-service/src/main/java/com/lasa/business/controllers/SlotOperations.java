/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Slot;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
public interface SlotOperations {

    @GetMapping
    public List<Slot> findAll();

    @GetMapping(value = "/{id}")
    public Slot findById(
            @ApiParam(name = "id", type = "integer", value = "Find a slot by id", required = true)
            @PathVariable Integer id);

    @PostMapping
    public List<Slot> createSlots(
            @ApiParam(name = "slots", type = "body", value = "Add a new slot")
            @RequestBody List<Slot> slots);

    @PutMapping
    public List<Slot> updateSlots(
            @ApiParam(name = "slots", type = "body", value = "Update a slot by id", required = true)
            @RequestBody List<Slot> slots);

    @DeleteMapping
    public void deleteSlots(
            @ApiParam(name = "ids", type = "body", value = "Delete a slot by id", required = true)
            @RequestBody List<Integer> ids);
}
