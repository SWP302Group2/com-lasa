package com.lasa.data.validator.model;

import com.lasa.data.model.request.BookingQuestionRequestModel;
import com.lasa.data.repo.repository.QuestionRepository;
import com.lasa.data.validator.ValidBookingQuestionUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookingQuestionUpdateValidator implements ConstraintValidator<ValidBookingQuestionUpdate, BookingQuestionRequestModel> {
    private final QuestionRepository repository;

    @Autowired
    public BookingQuestionUpdateValidator(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(BookingQuestionRequestModel bookingQuestionRequestModel, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(bookingQuestionRequestModel.getQuestions()))
            return true;
        else {
            if(bookingQuestionRequestModel.getQuestions().isEmpty())
                return false;

            long count = repository. countByIdAndBookingRequestIdIn(
                    bookingQuestionRequestModel.getId(),
                    bookingQuestionRequestModel.getQuestions()
                            .stream()
                            .map(t -> t.getId())
                            .collect(Collectors.toList())
            );
            return count == bookingQuestionRequestModel.getQuestions().size();
        }
    }
}
