package com.lasa.data.validator.model;

import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.repo.repository.QuestionRepository;
import com.lasa.data.validator.ValidQuestionUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.stream.Collectors;

public class QuestionUpdateValidator implements ConstraintValidator<ValidQuestionUpdate, BookingRequestRequestModel> {
    private final QuestionRepository repository;

    @Autowired
    public QuestionUpdateValidator(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(BookingRequestRequestModel bookingRequestRequestModel, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(bookingRequestRequestModel.getQuestions()))
            return true;
        else {
            if(bookingRequestRequestModel.getQuestions().isEmpty())
                return false;

            int count = repository.countByIdAndBookingRequestIdIn(
                    bookingRequestRequestModel.getId(),
                    bookingRequestRequestModel.getQuestions()
                            .stream()
                            .map(t -> t.getId())
                            .collect(Collectors.toList())
            );
            return count == bookingRequestRequestModel.getQuestions().size();
        }
    }
}
