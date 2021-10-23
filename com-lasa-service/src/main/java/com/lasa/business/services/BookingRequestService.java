
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.dto.BookingRequestDTO;
import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.entity.utils.page.BookingRequestPage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */

@Service
public interface BookingRequestService {

    Page<BookingRequestDTO> findAll(BookingRequestPage bookingRequestPage, BookingRequestSearchCriteria searchCriteria);

    List<BookingRequestDTO> findAll(BookingRequestSearchCriteria searchCriteria);

    BookingRequestDTO findByBookingRequestId(Integer id);

    Boolean verifyBookingRequest(Integer studentId, Integer slotId);

    BookingRequest createBookingRequest(BookingRequest bookingRequest);

    BookingRequest updateBookingRequest(BookingRequest bookingRequest);

    void deleteBookingRequests(List<Integer> ids);

}
