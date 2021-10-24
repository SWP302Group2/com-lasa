/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.BookingRequestOperations;
import com.lasa.business.services.BookingRequestService;
import com.lasa.business.services.QuestionService;
import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.view.BookingRequestViewModel;
import com.lasa.data.model.view.QuestionViewModel;
import com.lasa.data.model.entity.BookingRequest_;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.criteria.QuestionSearchCriteria;
import com.lasa.data.model.utils.page.BookingRequestPage;
import com.lasa.security.appuser.MyUserDetails;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author wifil
 */
@RestController
@RequestMapping("/api/v1/booking-requests")
@Api(value = "booking-requests", description = "For Booking requests", tags = { "Booking requests" })
public class BookingRequestController implements BookingRequestOperations {

    private final BookingRequestService bookingRequestService;
    private final QuestionService questionService;
    private static final int QUESTIONS_SIZE = 5;
    private static final String QUESTIONS_SIZE_STRING = "FIVE";

    @Autowired
    public BookingRequestController(@Qualifier("BookingRequestServiceImplV1") BookingRequestService service,
                                    @Qualifier("QuestionServiceImplV1") QuestionService questionService) {
        this.bookingRequestService = service;
        this.questionService = questionService;
    }

    @Override
    public ResponseEntity<?> findWithArguments(BookingRequestPage bookingRequestPage, BookingRequestSearchCriteria searchCriteria) {
        if(bookingRequestPage.isPaging())
            return ResponseEntity.ok(bookingRequestService.findAll(bookingRequestPage, searchCriteria));
        else
            return ResponseEntity.ok(bookingRequestService.findAll(searchCriteria));

    }

    @Override
    public ResponseEntity<BookingRequestViewModel> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookingRequestService.findByBookingRequestId(id));
    }

    @Override
    public ResponseEntity<?> findByIdIncludeQuestions(Integer id) {
        BookingRequestViewModel bookingRequestDTO = bookingRequestService.findByBookingRequestId(id);

        if(Objects.isNull(bookingRequestDTO))
            return ResponseEntity.ok(null);

        List<Integer> bookingIds = new ArrayList<>();
        bookingIds.add(bookingRequestDTO.getId());
        QuestionSearchCriteria searchCriteria = QuestionSearchCriteria.builder()
                .bookingId(bookingIds)
                .build();
        List<QuestionViewModel> questionDTOS = questionService.findAll(searchCriteria);
        questionDTOS.stream()
                .forEach(t -> bookingRequestDTO.addQuestion(t));
        return ResponseEntity.ok(bookingRequestDTO);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<BookingRequestViewModel> createBookingRequest(@RequestBody BookingRequestRequestModel bookingRequest) throws ExceptionUtils.ArgumentException, ExceptionUtils.DuplicatedException {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //check questions size must <= 5, questions not empty, and student not already have a booking before
        if(Objects.isNull(bookingRequest.getQuestions()))
            throw new ExceptionUtils.ArgumentException(BookingRequest_.QUESTIONS.toUpperCase(Locale.ROOT) + "_IS_EMPTY");

        else if(bookingRequest.getQuestions().size() >= QUESTIONS_SIZE)
            throw new ExceptionUtils.ArgumentException(BookingRequest_.QUESTIONS.toUpperCase(Locale.ROOT) + "_IS_OVERFLOW_" + QUESTIONS_SIZE_STRING);

        else if(bookingRequestService.verifyBookingRequest(userDetails.getId(), bookingRequest.getSlotId()).equals(false))
            throw new ExceptionUtils.DuplicatedException("BOOKING_REQUEST_DUPLICATED");

        else if(bookingRequest.getQuestions()
                .stream()
                .anyMatch(t -> Objects.isNull(t.getContent())))
            throw new ExceptionUtils.ArgumentException("CONTENT_IS_EMPTY");

        bookingRequest.setStudentId(userDetails.getId());
        bookingRequest.setStatus(1);
        System.out.println(bookingRequest.getStudentId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookingRequestService.createBookingRequest(bookingRequest));

    }

    @Override
    public ResponseEntity<BookingRequestViewModel> updateBookingRequest(@RequestBody BookingRequestRequestModel BookingRequest) {
        return ResponseEntity.ok(bookingRequestService.updateBookingRequest(BookingRequest));
    }

    @Override
    public void deleteBookingRequests(@RequestBody List<Integer> ids) {
        bookingRequestService.deleteBookingRequests(ids);
    }


}
