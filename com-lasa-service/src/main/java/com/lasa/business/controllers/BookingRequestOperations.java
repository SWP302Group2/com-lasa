/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.BookingRequest;
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
    ResponseEntity<?> getBookingRequests(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "studentId", required = false) Integer studentId,
            @RequestParam(value = "slotId", required = false) Integer slotId
            );

    @RequestMapping(value = "/status")
    ResponseEntity<?> getBookingRequestsByStatus(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "value", required = true) Integer status
    );


    @GetMapping(value = "/{id}")
    public BookingRequest getBookingRequestById(@PathVariable("id") Integer id);

    @GetMapping(value = "/{id}/questions")
    public BookingRequest getBookingRequestWithQuestionsById(@PathVariable("id") Integer id);
    
    @PostMapping
    public BookingRequest createBookingRequest(@RequestBody BookingRequest bookingRequest);
    
    @PutMapping
    public BookingRequest updateBookingRequest(@RequestBody BookingRequest BookingRequest);
    
    @DeleteMapping
    public void deleteBookingRequests(@RequestBody List<Integer> ids);
}
