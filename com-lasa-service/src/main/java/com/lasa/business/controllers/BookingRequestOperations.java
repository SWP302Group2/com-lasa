/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers;

import com.lasa.data.model.request.BookingQuestionRequestModel;
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

    @PostMapping(value = "/{id}/questions")
    ResponseEntity<?> addBookingQuestions(@PathVariable(value = "id") Integer id,
                                          @Validated(PostValidator.class) @RequestBody BookingQuestionRequestModel model);
    
    @PutMapping
    ResponseEntity<?> updateBookingRequest(@ApiParam(name = "bookingRequest", type = "body", value = "Update a booking request by id")
                                           @Validated(PutValidator.class) @RequestBody BookingRequestRequestModel BookingRequest);

    @PutMapping(value = "/{id}/questions")
    ResponseEntity<?> updateBookingQuestions(@PathVariable(value = "id") Integer id,
                                             @Validated(PutValidator.class) @RequestBody BookingQuestionRequestModel model);
    
    @DeleteMapping
    ResponseEntity<?> deleteBookingRequests(@ApiParam(name = "id", type = "param", value = "By id, you may remove booking request")
                                            @RequestParam List<Integer> id) throws ExceptionUtils.DeleteException;

//    @GetMapping(value = "/confirm/{id}/{status}")
//    ResponseEntity<?> confirmBookingRequest(@ApiParam(name = "id", type = "Integer", value = "Confirm booking request")
//                               @PathVariable("id") Integer id,
//                               @PathVariable("status") Integer status) throws MessagingException;
    @DeleteMapping(value = "/{bookingId}/questions")
    ResponseEntity<?> deleteBookingQuestions(@PathVariable("bookingId") Integer bookingId,
                                             @RequestParam List<Integer> id) throws ExceptionUtils.DeleteException;

}
