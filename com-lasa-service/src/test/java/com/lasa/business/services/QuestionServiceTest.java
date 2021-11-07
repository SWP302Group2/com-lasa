package com.lasa.business.services;

import com.lasa.business.services.implv1.QuestionServiceImpl;
import com.lasa.data.model.entity.BookingRequest;
import com.lasa.data.model.entity.Question;
import com.lasa.data.model.request.QuestionRequestModel;
import com.lasa.data.model.view.QuestionViewModel;
import com.lasa.data.repo.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionServiceImpl questionService;

    private BookingRequest bookingRequest;
    private List<QuestionRequestModel> questionModels;
    private QuestionRequestModel questionRequestModel;
    private List<Question> questionList;

    @BeforeEach
    void setUp() {
        questionService = new QuestionServiceImpl(questionRepository);

        bookingRequest = new BookingRequest();
        bookingRequest.setId(1);
        bookingRequest.setTitle("I WILL HELP YOU");

        questionRequestModel = new QuestionRequestModel();
        questionRequestModel.setId(1);
        questionRequestModel.setBookingId(bookingRequest.getId());

        questionModels = new ArrayList<>();
        questionModels.add(questionRequestModel);

        questionList = new ArrayList<>();
        questionList.add(questionRequestModel.toEntity());

    }

    @Test
    void findAll() {
    }

    @Test
    void testFindAll() {
    }

    @Test
    void shouldReturnIdWhenFindById() {
        Optional<Question> optionalQuestion = Optional.of(questionRequestModel.toEntity());
        when(questionRepository.findById(1))
                .thenReturn(optionalQuestion);
        QuestionViewModel result = questionService.findById(1);
        assertEquals(1, result.getId());
    }

    @Test
    void shouldCreateQuestions() {
        when(questionRepository.saveAll(questionList))
                .thenReturn(questionList);
        List<QuestionViewModel> viewModels = questionService.createQuestions(questionModels);
        assertEquals(1, viewModels.get(0).getId());
    }

    @Test
    void mustDeleteQuestion(){
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        questionService.deleteQuestion(ids);
        verify(questionRepository).deleteAllById(ids);
    }
}