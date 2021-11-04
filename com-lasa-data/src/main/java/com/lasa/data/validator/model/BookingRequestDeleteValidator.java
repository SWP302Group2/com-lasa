package com.lasa.data.validator.model;

import com.lasa.data.model.request.BookingQuestionDeleteRequestModel;
import com.lasa.data.repo.repository.QuestionRepository;
import com.lasa.data.validator.ValidBookingQuestionDelete;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class BookingRequestDeleteValidator implements ConstraintValidator<ValidBookingQuestionDelete, BookingQuestionDeleteRequestModel> {

    private final QuestionRepository repository;

    @Autowired
    public BookingRequestDeleteValidator(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(BookingQuestionDeleteRequestModel model, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.nonNull(model.getStudentId()) && Objects.nonNull(model.getQuestionIds())) {
            if(model.getQuestionIds().isEmpty())
                return false;
            else {
                return repository.countAvailableQuestionsForDelete(model.getQuestionIds(), model.getStudentId()) == model.getQuestionIds().size();
                //update check >= 1 question later
            }
        }

        return false;
    }
}
