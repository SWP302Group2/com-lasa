/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.page.BookingRequestPage;
import com.lasa.data.validator.group.PostValidator;
import com.lasa.data.validator.group.PutValidator;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

/**
 *
 * @author hai
 */
@RequestMapping("/default")
@Validated
public interface BookingRequestOperations {

    @GetMapping
    ResponseEntity<?> findWithArguments(@Valid BookingRequestPage bookingRequestPage,
                                        @Valid BookingRequestSearchCriteria searchCriteria);

    @GetMapping(value = "/{id}")
    ResponseEntity<?> findById(@ApiParam(name = "id", type = "Integer", value = "Get booking request by id")
                               @Min(0) @PathVariable("id") Integer id);

    @GetMapping(value = "/{id}/questions")
    ResponseEntity<?> findByIdIncludeQuestions(@ApiParam(name = "id", type = "Integer", value = "Get booking request by id")
                                               @Min(1) @PathVariable("id") Integer id);


    @PostMapping
    ResponseEntity<?> createBookingRequest(@ApiParam(name = "bookingRequest", type = "body", value = "Add a new booking request")
                                           @Validated(PostValidator.class) @RequestBody BookingRequestRequestModel bookingRequest)
            throws ExceptionUtils.ArgumentException, ExceptionUtils.DuplicatedException;
    
    @PutMapping
    ResponseEntity<?> updateBookingRequest(@ApiParam(name = "bookingRequest", type = "body", value = "Update a booking request by id")
                                           @Validated(PutValidator.class) @RequestBody BookingRequestRequestModel BookingRequest);
    
    @DeleteMapping
    ResponseEntity<?> deleteBookingRequests(@ApiParam(name = "ids", type = "body", value = "By id, you may remove booking request")
                                            @RequestBody List<Integer> ids);
}
