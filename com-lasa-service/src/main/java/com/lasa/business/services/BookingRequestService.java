
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.entity.BookingRequest;
import java.util.List;
import java.util.Optional;


import com.lasa.data.entity.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.entity.utils.page.BookingRequestPage;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 *
 * @author hai
 */

@Service
public interface BookingRequestService {

    Page<BookingRequest> findAll(BookingRequestPage bookingRequestPage, BookingRequestSearchCriteria searchCriteria);

    BookingRequest findByBookingRequestId(Integer id);

    BookingRequest createBookingRequest(BookingRequest bookingRequest);

    BookingRequest updateBookingRequest(BookingRequest bookingRequest);

    void deleteBookingRequests(List<Integer> ids);
}
