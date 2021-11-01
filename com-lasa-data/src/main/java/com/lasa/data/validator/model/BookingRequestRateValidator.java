package com.lasa.data.validator.model;

import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.repo.repository.BookingRequestRepository;
import com.lasa.data.validator.ValidBookingRequestRate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class BookingRequestRateValidator implements ConstraintValidator<ValidBookingRequestRate, BookingRequestRequestModel> {
    private final BookingRequestRepository repository;

    @Autowired
    public BookingRequestRateValidator(BookingRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(BookingRequestRequestModel model, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(model.getRating()))
            return true;
        else if(model.getRating() >= 0 && model.getRating() <= 10)
            return repository.countRateableBooking(model.getId()) == 1;

        return false;
    }
}
