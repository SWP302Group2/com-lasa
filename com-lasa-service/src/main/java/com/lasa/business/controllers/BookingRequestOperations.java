/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.entity.BookingRequest;
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
    ResponseEntity<?> getBookingRequests(
            @ApiParam(name ="page",type ="integer",value = "Paging",defaultValue = "0") @RequestParam Integer pageNumber,
            @ApiParam(name ="size",type ="integer",value ="The size you want view", defaultValue = "10") @RequestParam Integer pageSize,
            @ApiParam(name ="studentId",type ="integer",value ="Get student by id") @RequestParam Integer studentId,
            @ApiParam(name ="slotId",type ="integer",value ="Get slot by id", required = false) @RequestParam Integer slotId
            );
    @RequestMapping(value = "/status")
    ResponseEntity<?> getBookingRequestsByStatus(
            @ApiParam(name ="page",type ="integer",value = "Paging",required = false, defaultValue = "0") @RequestParam Integer pageNumber,
            @ApiParam(name ="size",type ="integer",value = "Size",required = false, defaultValue = "10") @RequestParam Integer pageSize,
            @ApiParam(name ="status",type ="integer",value = "Status of booking request") @RequestParam  Integer status
    );


    @GetMapping(value = "/{id}")
    public BookingRequest getBookingRequestById(
            @ApiParam(name = "id", type = "integer", value = "Get booking request by id")
            @PathVariable("id") Integer id);

    @GetMapping(value = "/{id}/questions")
    public BookingRequest getBookingRequestWithQuestionsById(
            @ApiParam(name = "id", type = "integer", value = "Get question by id")
            @PathVariable("id") Integer id);
    
    @PostMapping
    public BookingRequest createBookingRequest(
            @ApiParam(name = "bookingRequest", type = "body", value = "Add a new booking request")
            @RequestBody BookingRequest bookingRequest);
    
    @PutMapping
    public BookingRequest updateBookingRequest(
            @ApiParam(name = "bookingRequest", type = "body", value = "Update a booking request by id")
            @RequestBody BookingRequest BookingRequest);
    
    @DeleteMapping
    public void deleteBookingRequests(
             @ApiParam(name = "ids", type = "body", value = "By id, you may remove booking request")
            @RequestBody List<Integer> ids);
}
