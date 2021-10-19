
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.BookingRequest;
import com.lasa.data.entity.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.entity.utils.page.BookingRequestPage;
import com.lasa.security.appuser.MyUserDetails;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */

@Service
public interface BookingRequestService {

    Page<BookingRequest> findAll(BookingRequestPage bookingRequestPage, BookingRequestSearchCriteria searchCriteria);

    List<BookingRequest> findAll(BookingRequestSearchCriteria searchCriteria);

    BookingRequest findByBookingRequestId(Integer id);

    Boolean verifyBookingRequest(Integer studentId, Integer slotId);

    BookingRequest createBookingRequest(BookingRequest bookingRequest);

    BookingRequest updateBookingRequest(BookingRequest bookingRequest);

    void deleteBookingRequests(List<Integer> ids);

}
