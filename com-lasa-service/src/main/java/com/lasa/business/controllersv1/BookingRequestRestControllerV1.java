/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllersv1;

import com.lasa.business.controllers.BookingRequestOperations;
import com.lasa.data.entity.BookingRequest;
import com.lasa.business.services.BookingRequestService;
import java.util.List;

import com.lasa.data.entity.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.entity.utils.page.BookingRequestPage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author wifil
 */
@RestController
@RequestMapping("/api/v1/booking-requests")
@Api(value = "booking-requests", description = "For Booking requests", tags = { "Booking requests" })
public class BookingRequestRestControllerV1 implements BookingRequestOperations {

    private final BookingRequestService bookingRequestService;

    @Autowired
    public BookingRequestRestControllerV1(@Qualifier("BookingRequestServiceImplV1") BookingRequestService service) {
        this.bookingRequestService = service;
    }

    @Override
    public ResponseEntity<?> findAll(BookingRequestPage bookingRequestPage, BookingRequestSearchCriteria searchCriteria) {
        if(bookingRequestPage.isPaging())
            return ResponseEntity.ok(bookingRequestService.findAll(bookingRequestPage, searchCriteria));
        else
            return ResponseEntity.ok(bookingRequestService.findAll(searchCriteria));

    }

    @Override
    public BookingRequest findById(@PathVariable Integer id) {
        return bookingRequestService.findByBookingRequestId(id);
    }

    @Override
    public BookingRequest createBookingRequest(@RequestBody BookingRequest bookingRequest) {
        return bookingRequestService.createBookingRequest(bookingRequest);
    }

    @Override
    public BookingRequest updateBookingRequest(@RequestBody BookingRequest BookingRequest) {
        return bookingRequestService.updateBookingRequest(BookingRequest);
    }

    @Override
    public void deleteBookingRequests(@RequestBody List<Integer> ids) {
        bookingRequestService.deleteBookingRequests(ids);
    }


}
