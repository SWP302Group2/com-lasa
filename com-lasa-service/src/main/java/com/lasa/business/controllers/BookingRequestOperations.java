/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.entity.utils.page.BookingRequestPage;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author hai
 */
@RequestMapping("/default")
public interface BookingRequestOperations {

    @GetMapping
    ResponseEntity<?> findAll(BookingRequestPage bookingRequestPage, BookingRequestSearchCriteria searchCriteria);

    @GetMapping(value = "/{id}")
    BookingRequest findById(
            @ApiParam(name = "id", type = "Integer", value = "Get booking request by id")
            @PathVariable("id") Integer id);

    @PostMapping
    BookingRequest createBookingRequest(
            @ApiParam(name = "bookingRequest", type = "body", value = "Add a new booking request")
            @RequestBody BookingRequest bookingRequest);
    
    @PutMapping
    BookingRequest updateBookingRequest(
            @ApiParam(name = "bookingRequest", type = "body", value = "Update a booking request by id")
            @RequestBody BookingRequest BookingRequest);
    
    @DeleteMapping
    void deleteBookingRequests(
            @ApiParam(name = "ids", type = "body", value = "By id, you may remove booking request")
            @RequestBody List<Integer> ids);
}
