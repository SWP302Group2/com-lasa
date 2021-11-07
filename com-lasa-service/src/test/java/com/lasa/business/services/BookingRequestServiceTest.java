package com.lasa.business.services;

import com.lasa.business.services.implv1.BookingRequestServiceImpl;
import com.lasa.business.services.implv1.StudentServiceImpl;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Question;
import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.model.request.QuestionRequestModel;
import com.lasa.data.model.utils.criteria.BookingRequestSearchCriteria;
import com.lasa.data.model.utils.page.BookingRequestPage;
import com.lasa.data.model.utils.specification.BookingRequestSpecification;
import com.lasa.data.model.view.BookingRequestViewModel;
import com.lasa.data.repo.repository.BookingRequestRepository;
import com.lasa.data.repo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BookingRequestServiceTest {

    @Mock
    private BookingRequestRepository bookingRequestRepository;
    @InjectMocks
    private BookingRequestServiceImpl bookingRequestService;
    @Mock
    private BookingRequestPage page;

    private BookingRequestSearchCriteria bookingRequestSearchCriteria;
    private BookingRequestRequestModel bookingRequestRequestModel;
    @Mock
    private StudentRepository studentRepository;
    private StudentServiceImpl studentService;
    private BookingRequest bookingRequest;
    @BeforeEach
    void setUp() {
        bookingRequestService = new BookingRequestServiceImpl(bookingRequestRepository, studentService, studentRepository);

        LocalDateTime now = LocalDateTime.now();
        bookingRequest = new BookingRequestRequestModel().toEntity();
        bookingRequest.setId(1);
        bookingRequest.setStatus(1);
        bookingRequest.setCreateTime(now);
        bookingRequest.setQuestions(null);
        bookingRequest.setRating(1);
        bookingRequest.setSlotId(1);
        bookingRequest.setTitle("HELP");
        bookingRequest.setStudentId(1);
        bookingRequest.setTopicId(1);

        List <Integer> id = new  ArrayList();
        id.add(1);

        bookingRequestSearchCriteria = new BookingRequestSearchCriteria();
        bookingRequestSearchCriteria.setStatus(1);
        bookingRequestSearchCriteria.setSlotId(id);
        bookingRequestSearchCriteria.setStudentId(id);
        bookingRequestSearchCriteria.setTopicId(id);
        bookingRequestSearchCriteria.setGetStudent(false);

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
    }

    @Test
    public void returnAllListInFindAll(){
//        List<BookingRequest> list = new ArrayList<>();
//        list.add(bookingRequest);
//        when(bookingRequestRepository
//                .findAll(BookingRequestSpecification.searchSpecification(bookingRequestSearchCriteria)))
//                .thenReturn(list);
//        List<BookingRequestViewModel> bookingRequestViewModels =
//                bookingRequestService.findAll(bookingRequestSearchCriteria)
//                        .stream()
//                        .map(t -> new BookingRequestViewModel())
//                        .collect(Collectors.toList());;
//
//        System.out.println("THIS IS: " + bookingRequestViewModels.get(0).getTitle());
//        assertEquals(1,bookingRequestViewModels.get(0).getRating());

    }
    @Test
    public void givenBookingIdShouldReturnBookingRequest(){
        bookingRequestService.findByBookingRequestId(2);
        Mockito.verify(bookingRequestRepository).findById(2);

        Optional<BookingRequest> bookingRequestOptional = Optional.of(bookingRequest);
        when(bookingRequestRepository.findById(1))
                .thenReturn(bookingRequestOptional);
        BookingRequestViewModel result = bookingRequestService.findByBookingRequestId(1);
        assertEquals(1, result.getId());
    }

    @Test
    public void shouldReturnListBookingRequest(){

    }

//    @Test
//    public void returnVerifyBookingRequestWithFalse(){
//        bookingRequestService.verifyBookingRequest(1,1);
//        Mockito.verify(bookingRequestRepository).findBookingRequestByStudentIdAndSlotId(1,1);
//
//        Optional<BookingRequest> bookingRequestOptional = Optional.of(bookingRequest);
//        when(bookingRequestRepository.findBookingRequestByStudentIdAndSlotId(1,1))
//                .thenReturn(bookingRequestOptional);
//        Boolean result = bookingRequestService.verifyBookingRequest(1,1);
//        assertFalse(result);
//    }

    @Test
    public void shouldCreateBooking(){
        when(bookingRequestRepository.save(bookingRequestRequestModel.toEntity()))
                .thenReturn(bookingRequestRequestModel.toEntity());
        BookingRequestViewModel result = bookingRequestService.createBookingRequest(bookingRequestRequestModel);
        assertEquals(1,result.getId());
    }

    @Test
    public void shouldDeleteBooking(){
        List<Integer> ids = new ArrayList<>();
        ids.add(bookingRequest.getId());
        bookingRequestService.deleteBookingRequests(ids);
        Mockito.verify(bookingRequestRepository)
                .findAllById(ids);
    }

    @Test
    public void shouldUpdateBooking(){

    }

}
