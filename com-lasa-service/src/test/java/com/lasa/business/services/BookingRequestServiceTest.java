package com.lasa.business.services;

import com.lasa.business.services.implv1.BookingRequestServiceImpl;
import com.lasa.business.services.implv1.StudentServiceImpl;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.request.QuestionRequestModel;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.page.BookingRequestPage;
import com.lasa.data.model.view.BookingRequestViewModel;
import com.lasa.data.repo.repository.BookingRequestRepository;
import com.lasa.data.repo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BookingRequestServiceTest {

    @Mock
    private BookingRequestRepository bookingRequestRepository;
    @InjectMocks
    private BookingRequestServiceImpl bookingRequestService;
    @Mock
    private BookingRequestPage page;
    @Mock
    private StudentRepository studentRepository;
    private StudentServiceImpl studentService;

    private BookingRequestSearchCriteria bookingRequestSearchCriteria;
    private BookingRequestRequestModel bookingRequestRequestModel;
    private List<BookingRequest> bookingRequests;
    private Specification<BookingRequest> searchSpecification;
    @BeforeEach
    void setUp() {
        bookingRequestService = new BookingRequestServiceImpl(bookingRequestRepository, studentService, studentRepository);

        LocalDateTime now = LocalDateTime.now();

        List <Integer> ids = new  ArrayList();
        ids.add(1);
        ids.add(2);

        page = new BookingRequestPage();
        page.setPage(1);
        page.setSize(10);

        QuestionRequestModel question = new QuestionRequestModel();
        question.setId(1);
        List<QuestionRequestModel> questions = new ArrayList<>();
        questions.add(question);

        bookingRequestRequestModel = new BookingRequestRequestModel();
        bookingRequestRequestModel.setId(1);
        bookingRequestRequestModel.setStatus(1);
        bookingRequestRequestModel.setCreateTime(now);
        bookingRequestRequestModel.setQuestions(questions);
        bookingRequestRequestModel.setRating(1);
        bookingRequestRequestModel.setSlotId(1);
        bookingRequestRequestModel.setTitle("HELP");
        bookingRequestRequestModel.setStudentId(1);
        bookingRequestRequestModel.setTopicId(1);

        bookingRequests = new ArrayList<>();
        bookingRequests.add(bookingRequestRequestModel.toEntity());

        bookingRequestSearchCriteria = new BookingRequestSearchCriteria();
        bookingRequestSearchCriteria.setStatus(1);
        bookingRequestSearchCriteria.setSlotId(ids);
        bookingRequestSearchCriteria.setStudentId(ids);
        bookingRequestSearchCriteria.setTopicId(ids);
        bookingRequestSearchCriteria.setGetStudent(true);
    }

    @Test
    void returnAllListInFindAll(){
//
//        when(bookingRequestRepository.findAll
//                (BookingRequestSpecification.searchSpecification(bookingRequestSearchCriteria)))
//                .thenReturn(bookingRequests);
//
//        List<BookingRequestViewModel> results = bookingRequestService.findAll(bookingRequestSearchCriteria);
//
//        assertEquals(0, results.size());

    }
    @Test
    void givenBookingIdShouldReturnBookingRequest(){
        bookingRequestService.findByBookingRequestId(2);
        Mockito.verify(bookingRequestRepository).findById(2);

        Optional<BookingRequest> bookingRequestOptional = Optional.of(bookingRequestRequestModel.toEntity());
        when(bookingRequestRepository.findById(1))
                .thenReturn(bookingRequestOptional);
        BookingRequestViewModel result = bookingRequestService.findByBookingRequestId(1);
        assertEquals(1, result.getId());
    }

    @Test
    void shouldReturnListBookingRequest(){

    }

    @Test
    public void returnVerifyBookingRequestWithTrue(){

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        Long list = Long.valueOf(ids.size());

        when(bookingRequestRepository.countAvailableBookingForDelete(bookingRequestRequestModel.getStudentId(), ids))
                .thenReturn(list);
        boolean result = bookingRequestService
                .verifyBookingRequestForDelete(bookingRequestRequestModel.getStudentId(), ids);
        assertTrue(result);
    }

    @Test
    void shouldCreateBooking(){
        when(bookingRequestRepository.save(bookingRequestRequestModel.toEntity()))
                .thenReturn(bookingRequestRequestModel.toEntity());
        BookingRequestViewModel result = bookingRequestService.createBookingRequest(bookingRequestRequestModel);
        assertEquals(1,result.getId());
    }

    @Test
    void shouldDeleteBooking(){
        List<Integer> deleteIds = new ArrayList<>();
        deleteIds.add(bookingRequestRequestModel.toEntity().getId());
        bookingRequestService.deleteBookingRequests(deleteIds);
        Mockito.verify(bookingRequestRepository)
                .findAllById(deleteIds);
    }

    @Test
    void shouldUpdateBooking(){
//        Optional<BookingRequest> bookingRequestOptional = Optional.of(bookingRequestRequestModel.toEntity());
//        when(bookingRequestRepository.findById(bookingRequestRequestModel.toEntity().getId()))
//                .thenReturn(bookingRequestOptional);
//                .thenReturn(bookingRequestOptional.map(t-> new BookingRequestRequestModel().toEntity()));

//        BookingRequestViewModel bookingWithIdLooking = bookingRequestService
//                .findByBookingRequestId(bookingRequestRequestModel.toEntity().getId());
//
//        if (bookingWithIdLooking.getTitle() != null) {
//            bookingRequestRequestModel.toEntity().setTitle("HAPPY");
//        }
//
//        when(bookingRequestRepository.save(bookingRequestRequestModel.toEntity()))
//                .thenReturn(bookingRequestRequestModel.toEntity());
//
//        BookingRequestRequestModel view = new BookingRequestRequestModel();
//        view.setId(bookingWithIdLooking.getId());
//        view.setStatus(bookingWithIdLooking.getStatus());
//        view.setTitle(bookingRequestRequestModel.toEntity().getTitle());
//        System.out.println(view.toEntity().toString());
//        System.out.println(bookingWithIdLooking.getId());
//        BookingRequestViewModel result = bookingRequestService.updateBookingRequest(bookingRequestRequestModel);
//        assertEquals("HAPPY", result.getTitle());

    }

}
