/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.model.request.SlotBookingRequestModel;
import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.model.utils.criteria.SlotSearchCriteria;
import com.lasa.data.model.utils.page.SlotPage;
import com.lasa.data.validator.group.PostValidator;
import com.lasa.data.validator.group.PutValidator;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 *
 * @author Admin
 */
@RequestMapping("/default")
@Validated
public interface SlotOperations {

    @GetMapping
    ResponseEntity<?> findWithArguments(@Valid SlotSearchCriteria searchCriteria, SlotPage slotPage) throws ExceptionUtils.ArgumentException;

    @GetMapping(value = "/{id}")
    ResponseEntity<?> findById(@ApiParam(name = "id", type = "Integer", value = "Find a slot by id", required = true)
                               @Min(value = 1) @PathVariable(value = "id") Integer id);

    @GetMapping(value = "/{id}/booking-requests")
    ResponseEntity<?> findByIdIncludeBookingRequests(@ApiParam(name = "id", type = "Integer", value = "Find a slot by id", required = true)
                                                     @Min(value = 1) @PathVariable Integer id,
                                                     @Min(value = 1) @Max(value = 5) @RequestParam(value = "status", required = false) Integer status);



    @PostMapping
    ResponseEntity<?> createSlot(@ApiParam(name = "slots", type = "body", value = "Add a new slot")
                                           @Validated(value = PostValidator.class) @RequestBody SlotRequestModel model);

    @PutMapping
    ResponseEntity<?> updateSlots(@ApiParam(name = "slots", type = "body", value = "Update a slot by id", required = true)
                                  @Validated(PutValidator.class) @RequestBody SlotRequestModel slots);

    @PutMapping(value = "/{id}/booking-requests")
    ResponseEntity<?> updateBookingRequests(@Min(value = 1) @PathVariable("id") Integer id,
                                            @Validated(value = PutValidator.class) @RequestBody SlotBookingRequestModel model) throws MessagingException;

    @DeleteMapping
    void deleteSlots(
            @ApiParam(name = "ids", type = "body", value = "Delete a slot by id", required = true)
            @RequestBody List<Integer> ids);
}
