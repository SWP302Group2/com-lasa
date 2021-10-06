
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.BookingRequest;
import java.util.List;
import java.util.Optional;


import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author hai
 */

@Service
public interface BookingRequestService {

    public List<BookingRequest> findAllBookingRequests();

    Page<BookingRequest> findPageBookingRequest(Integer pageNumber, Integer pageSize);

    Page<BookingRequest> findPageBookingRequestByStudentId(Integer page, Integer size, Integer studentId);

    Page<BookingRequest> findPageBookingRequestBySlotId(Integer page, Integer size, Integer slotId);

    Page<BookingRequest> findPageBookingRequestByStatus(Integer page, Integer size, Integer status);

    public BookingRequest findBookingRequestAndGetQuestion(Integer id);

    public BookingRequest findByBookingRequestId(Integer id);

    public long countBookingRequest();

    public BookingRequest createBookingRequest(BookingRequest bookingRequest);

    public BookingRequest updateBookingRequest(BookingRequest bookingRequest);

    public void deleteBookingRequests(List<Integer> ids);
}
