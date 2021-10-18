/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.BookingRequestOperations;
import com.lasa.business.services.BookingRequestService;
import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.entity.utils.page.BookingRequestPage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author wifil
 */
@RestController
@RequestMapping("/api/v1/booking-requests")
@Api(value = "booking-requests", description = "For Booking requests", tags = { "Booking requests" })
public class BookingRequestController implements BookingRequestOperations {

    private final BookingRequestService bookingRequestService;

    @Autowired
    public BookingRequestController(@Qualifier("BookingRequestServiceImplV1") BookingRequestService service) {
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
