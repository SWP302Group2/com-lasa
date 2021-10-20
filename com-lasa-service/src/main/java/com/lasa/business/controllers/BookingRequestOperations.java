/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.entity.utils.page.BookingRequestPage;
import com.lasa.security.appuser.MyUserDetails;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

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
    ResponseEntity<?> createBookingRequest(
            @ApiParam(name = "bookingRequest", type = "body", value = "Add a new booking request")
            @RequestBody BookingRequest bookingRequest
    ) throws ExceptionUtils.ArgumentException, ExceptionUtils.DuplicatedException;
    
    @PutMapping
    BookingRequest updateBookingRequest(
            @ApiParam(name = "bookingRequest", type = "body", value = "Update a booking request by id")
            @RequestBody BookingRequest BookingRequest);
    
    @DeleteMapping
    void deleteBookingRequests(
            @ApiParam(name = "ids", type = "body", value = "By id, you may remove booking request")
            @RequestBody List<Integer> ids);
}
