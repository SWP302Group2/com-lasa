/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lasa.business.controllers.implv1;

import com.lasa.business.controllers.BookingRequestOperations;
import com.lasa.business.controllers.utils.authorization.IsAdmin;
import com.lasa.business.controllers.utils.authorization.IsStudent;
import com.lasa.business.services.BookingRequestService;
import com.lasa.business.services.EmailSenderService;
import com.lasa.business.services.QuestionService;
import com.lasa.business.services.StudentService;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.request.BookingQuestionDeleteRequestModel;
import com.lasa.data.model.request.BookingQuestionRequestModel;
import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.utils.criteria.StudentSearchCriteria;
import com.lasa.data.model.view.BookingRequestViewModel;
import com.lasa.data.model.view.QuestionViewModel;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.criteria.QuestionSearchCriteria;
import com.lasa.data.model.utils.page.BookingRequestPage;
import com.lasa.data.model.view.StudentViewModel;
import com.lasa.security.appuser.MyUserDetails;
import com.lasa.security.utils.exception.ExceptionUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private final EmailSenderService emailSenderService;

    @Autowired
    public BookingRequestController(@Qualifier("BookingRequestServiceImplV1") BookingRequestService service,
                                    @Qualifier("QuestionServiceImplV1") QuestionService questionService,
                                    @Qualifier("StudentServiceImplV1") StudentService studentService,
                                    @Qualifier("EmailSenderServiceImplV1") EmailSenderService emailSenderService) {
        this.bookingRequestService = service;
        this.questionService = questionService;
        this.studentService = studentService;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public ResponseEntity<?> findWithArguments(BookingRequestPage bookingRequestPage,
                                               BookingRequestSearchCriteria searchCriteria) {
        if(bookingRequestPage.isPaging()) {
            Page<BookingRequestViewModel> page = bookingRequestService.findAll(bookingRequestPage, searchCriteria);

            if(searchCriteria.getGetStudent().equals(true)) {
                List<Integer> studentIds = page.stream()
                        .map(t -> t.getStudentId())
                        .collect(Collectors.toList());

                List<StudentViewModel> students = getStudents(studentIds);
                page.stream()
                        .forEach(t -> {
                            StudentViewModel student = students.stream()
                                    .filter(x -> x.getId().equals(t.getStudentId()))
                                    .findAny()
                                    .get();
                            t.setStudent(student);
                        });
            }

            return ResponseEntity.ok(page);
        }
        else {
            List<BookingRequestViewModel> list = bookingRequestService.findAll(searchCriteria);

            if(searchCriteria.getGetStudent().equals(true)) {
                List<Integer> studentIds = list.stream()
                        .map(t -> t.getStudentId())
                        .collect(Collectors.toList());

                List<StudentViewModel> students = getStudents(studentIds);
                list.stream()
                        .forEach(t -> {
                            StudentViewModel student = students.stream()
                                    .filter(x -> x.getId().equals(t.getStudentId()))
                                    .findAny()
                                    .get();
                            t.setStudent(student);
                        });
            }

            return ResponseEntity.ok(list);
        }


    }

    private List<StudentViewModel> getStudents(List<Integer> ids) {
        StudentSearchCriteria searchCriteria = StudentSearchCriteria.builder()
                .studentId(ids)
                .build();
        return studentService.findWithArgument(searchCriteria);
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
    @IsAdmin
    public ResponseEntity<?> deleteBookingRequests(@RequestBody List<Integer> ids) {
        bookingRequestService.deleteBookingRequests(ids);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

//    @Override
//    public ResponseEntity<?> confirmBookingRequest(@PathVariable  Integer id,
//                                      @PathVariable Integer status) throws MessagingException {
//         bookingRequestService.confirmBookingRequest(id, status);
//         return ResponseEntity.status(HttpStatus.OK).build();
//    }
    @Override
    @IsStudent
    @PreAuthorize("#model.studentId.equals(authentication.principal.id)")
    public ResponseEntity<?> deleteBookingQuestions(Integer id, BookingQuestionDeleteRequestModel model) {
        questionService.deleteQuestion(model.getQuestionIds());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
