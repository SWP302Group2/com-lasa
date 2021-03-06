/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.BookingRequestOperations;
import com.lasa.business.controllers.utils.authorization.IsStudent;
import com.lasa.business.services.BookingRequestService;
import com.lasa.business.services.QuestionService;
import com.lasa.business.services.StudentService;
import com.lasa.data.model.request.BookingQuestionRequestModel;
import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.criteria.QuestionSearchCriteria;
import com.lasa.data.model.utils.page.BookingRequestPage;
import com.lasa.data.model.view.BookingRequestViewModel;
import com.lasa.data.model.view.QuestionViewModel;
import com.lasa.security.appuser.MyUserDetails;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
    private final StudentService studentService;

    @Autowired
    public BookingRequestController(@Qualifier("BookingRequestServiceImplV1") BookingRequestService service,
                                    @Qualifier("QuestionServiceImplV1") QuestionService questionService,
                                    @Qualifier("StudentServiceImplV1") StudentService studentService) {
        this.bookingRequestService = service;
        this.questionService = questionService;
        this.studentService = studentService;
    }

    @Override
    public ResponseEntity<?> findWithArguments(BookingRequestPage bookingRequestPage,
                                               BookingRequestSearchCriteria searchCriteria) {
        if(bookingRequestPage.isPaging())
            return ResponseEntity.ok(bookingRequestService.findAll(bookingRequestPage, searchCriteria));
        else
            return ResponseEntity.ok(bookingRequestService.findAll(searchCriteria));

    }

    @Override
    public ResponseEntity<BookingRequestViewModel> findById(Integer id) {
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
    @Transactional
    @IsStudent
    @PreAuthorize("#model.studentId.equals(authentication.principal.id)")
    public ResponseEntity<BookingRequestViewModel> createBookingRequest(BookingRequestRequestModel model) {
        model.setStatus(1);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookingRequestService.createBookingRequest(model));

    }

    @Override
    @IsStudent
    @PreAuthorize("(#id.equals(#model.id)) && (#model.studentId.equals(authentication.principal.id))")
    public ResponseEntity<List<QuestionViewModel>> addBookingQuestions(Integer id, BookingQuestionRequestModel model) {
        model.getQuestions().stream()
                .forEach(t -> t.setBookingId(model.getId()));
        return ResponseEntity.ok(questionService.createQuestions(model.getQuestions()));
    }

    @Override
    @IsStudent
    @PreAuthorize("#model.studentId.equals(authentication.principal.id)")
    public ResponseEntity<BookingRequestViewModel> updateBookingRequest(BookingRequestRequestModel model) {
        return ResponseEntity.ok(bookingRequestService.updateBookingRequest(model));
    }

    @Override
    @IsStudent
    @PreAuthorize("(#id.equals(#model.id)) && (#model.studentId.equals(authentication.principal.id))")
    public ResponseEntity<List<QuestionViewModel>> updateBookingQuestions(Integer id, BookingQuestionRequestModel model) {
        return ResponseEntity.ok(questionService.updateQuestions(model.getQuestions()));
    }

    @Override
    @IsStudent
    public ResponseEntity<?> deleteBookingRequests(List<Integer> id) throws ExceptionUtils.DeleteException {
        if(!bookingRequestService.verifyBookingRequestForDelete(
                ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(),
                id))
            throw new ExceptionUtils.DeleteException("BOOKING_CAN_NOT_DELETE_OR_NOT_AVAILABLE");

        bookingRequestService.deleteBookingRequests(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @IsStudent
    public ResponseEntity<?> deleteBookingQuestions(Integer bookingId, List<Integer> id) throws ExceptionUtils.DeleteException {
        if(!questionService.verifyAvailableQuestionForDelete(
                bookingId,
                ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(),
                id
        ))
            throw new ExceptionUtils.DeleteException("QUESTION_CAN_NOT_DELETE_OR_NOT_AVAILABLE");

        questionService.deleteQuestion(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
