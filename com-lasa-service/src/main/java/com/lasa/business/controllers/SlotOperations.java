/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.Slot;
import com.lasa.data.entity.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.entity.utils.criteria.SlotSearchCriteria;
import com.lasa.data.entity.utils.page.SlotPage;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
public interface SlotOperations {

    @GetMapping
    ResponseEntity<?> findWithArguments(SlotSearchCriteria searchCriteria, SlotPage slotPage) throws ExceptionUtils.ArgumentException;

    @GetMapping(value = "/{id}")
    ResponseEntity<?> findById(@ApiParam(name = "id", type = "Integer", value = "Find a slot by id", required = true)
                               @PathVariable Integer id);

    @GetMapping(value = "/{id}/booking-requests")
    ResponseEntity<?> findByIdIncludeBookingRequests(@ApiParam(name = "id", type = "Integer", value = "Find a slot by id", required = true)
                                                     @PathVariable Integer id,
                                                     @RequestParam(value = "stauts", required = false) Integer status);



    @PostMapping
    ResponseEntity<Slot> createSlot(@ApiParam(name = "slots", type = "body", value = "Add a new slot")
                                    @RequestBody Slot slot) throws ExceptionUtils.ArgumentException;

    @PutMapping
    List<Slot> updateSlots(
            @ApiParam(name = "slots", type = "body", value = "Update a slot by id", required = true)
            @RequestBody List<Slot> slots);

    @DeleteMapping
    void deleteSlots(
            @ApiParam(name = "ids", type = "body", value = "Delete a slot by id", required = true)
            @RequestBody List<Integer> ids);
}
