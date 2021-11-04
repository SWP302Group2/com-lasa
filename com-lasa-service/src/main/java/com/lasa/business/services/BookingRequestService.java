
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.services;

import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.page.BookingRequestPage;
import com.lasa.data.model.view.BookingRequestViewModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author hai
 */

@Service
public interface BookingRequestService {

    Page<BookingRequestViewModel> findAll(BookingRequestPage bookingRequestPage, BookingRequestSearchCriteria searchCriteria);

    List<BookingRequestViewModel> findAll(BookingRequestSearchCriteria searchCriteria);

    BookingRequestViewModel findByBookingRequestId(Integer id);

    Boolean verifyBookingRequestForDelete(Integer studentId, List<Integer> bookingId);

    BookingRequestViewModel createBookingRequest(BookingRequestRequestModel bookingRequestModel);

    BookingRequestViewModel updateBookingRequest(BookingRequestRequestModel bookingRequest);

    void deleteBookingRequests(List<Integer> ids);


}
