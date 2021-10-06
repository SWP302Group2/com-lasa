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
import java.util.Optional;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.*;

/**
 *
 * @author wifil
 */
@RestController
@RequestMapping("/api/v1/booking-requests")
@CrossOrigin(
        allowCredentials = "true",
        origins = {"http://localhost:3000", "http://localhost:5500", "https://lasa-fpt.web.app"},
        allowedHeaders = {
                CONTENT_TYPE,
                CONTENT_LENGTH,
                HOST,
                USER_AGENT,
                ACCEPT,
                ACCEPT_ENCODING,
                CONNECTION,
                AUTHORIZATION
        },
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS}
)
@Api(value = "booking-requests", description = "For Booking requests", tags = { "Booking requests" })
public class BookingRequestRestControllerV1 implements BookingRequestOperations {

    private final BookingRequestService service;

    @Autowired
    public BookingRequestRestControllerV1(@Qualifier("BookingRequestServiceImplV1") BookingRequestService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> getBookingRequests(Integer page,
                                                Integer size,
                                                Integer studentId,
                                                Integer slotId) {
        if(studentId != null && slotId != null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }else if(studentId != null) {
            return ResponseEntity.ok(service.findPageBookingRequestByStudentId(page, size, studentId));

        }else if(slotId != null) {
            return ResponseEntity.ok(service.findPageBookingRequestBySlotId(page, size, slotId));
        }
        return ResponseEntity.ok(service.findPageBookingRequest(page, size));

    }

    @Override
    public ResponseEntity<?> getBookingRequestsByStatus(Integer page, Integer size, Integer status) {
        return ResponseEntity.ok(service.findPageBookingRequestByStatus(page, size, status));
    }

    @Override
    public BookingRequest getBookingRequestById(@PathVariable Integer id) {
        return service.findByBookingRequestId(id);
    }

    @Override
    public BookingRequest getBookingRequestWithQuestionsById(@PathVariable Integer id) {
        return service.findBookingRequestAndGetQuestion(id);
    }
    
    @Override
    public BookingRequest createBookingRequest(@RequestBody BookingRequest bookingRequest) {
        return service.createBookingRequest(bookingRequest);
    }

    @Override
    public BookingRequest updateBookingRequest(@RequestBody BookingRequest BookingRequest) {
        return service.updateBookingRequest(BookingRequest);
    }

    @Override
    public void deleteBookingRequests(@RequestBody List<Integer> ids) {
        service.deleteBookingRequests(ids);
    }


}
