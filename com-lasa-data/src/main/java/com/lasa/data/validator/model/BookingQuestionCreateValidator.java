package com.lasa.data.validator.model;

import com.lasa.data.model.request.BookingQuestionRequestModel;
import com.lasa.data.repo.repository.QuestionRepository;
import com.lasa.data.validator.ValidBookingQuestionCreate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class BookingQuestionCreateValidator implements ConstraintValidator<ValidBookingQuestionCreate, BookingQuestionRequestModel> {

    private final QuestionRepository repository;

    @Autowired
    public BookingQuestionCreateValidator(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(BookingQuestionRequestModel bookingQuestionRequestModel, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(bookingQuestionRequestModel)
                || Objects.isNull(bookingQuestionRequestModel.getStudentId())
                || Objects.isNull(bookingQuestionRequestModel.getId()))
            return false;
        else {
            if(bookingQuestionRequestModel.getQuestions().isEmpty())
                return false;
            else
                return repository.countByBookingRequestId(bookingQuestionRequestModel.getId()) + bookingQuestionRequestModel.getQuestions().size() <= 5;
        }


    }
}
