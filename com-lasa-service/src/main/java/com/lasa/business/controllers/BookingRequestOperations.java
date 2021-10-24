/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.page.BookingRequestPage;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author hai
 */
@RequestMapping("/default")
public interface BookingRequestOperations {

    @GetMapping
    ResponseEntity<?> findWithArguments(BookingRequestPage bookingRequestPage,
                                        BookingRequestSearchCriteria searchCriteria);

    @GetMapping(value = "/{id}")
    ResponseEntity<?> findById(@ApiParam(name = "id", type = "Integer", value = "Get booking request by id")
                               @PathVariable("id") Integer id);

    @GetMapping(value = "/{id}/questions")
    ResponseEntity<?> findByIdIncludeQuestions(@ApiParam(name = "id", type = "Integer", value = "Get booking request by id")
                                               @PathVariable("id") Integer id);


    @PostMapping
    ResponseEntity<?> createBookingRequest(@ApiParam(name = "bookingRequest", type = "body", value = "Add a new booking request")
                                           @RequestBody BookingRequestRequestModel bookingRequest) throws ExceptionUtils.ArgumentException, ExceptionUtils.DuplicatedException;
    
    @PutMapping
    ResponseEntity<?> updateBookingRequest(@ApiParam(name = "bookingRequest", type = "body", value = "Update a booking request by id")
                                           @RequestBody BookingRequestRequestModel BookingRequest);
    
    @DeleteMapping
    ResponseEntity<?> deleteBookingRequests(@ApiParam(name = "ids", type = "body", value = "By id, you may remove booking request")
                               @RequestBody List<Integer> ids);
}
