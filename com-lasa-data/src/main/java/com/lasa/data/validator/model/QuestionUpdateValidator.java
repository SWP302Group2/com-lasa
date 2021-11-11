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

    @Override
    public boolean isValid(BookingRequestRequestModel bookingRequestRequestModel, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(bookingRequestRequestModel.getQuestions())) {

        }
        else {
            if(bookingRequestRequestModel.getQuestions().isEmpty() || bookingRequestRequestModel.getQuestions().size() > 5 || bookingRequestRequestModel.getQuestions().size() < 1)
                return false;
        }

        return true;
    }
}
