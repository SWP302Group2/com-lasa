package com.lasa.data.validator.model;

import com.lasa.data.repo.repository.BookingRequestRepository;
import com.lasa.data.validator.ValidBookingRequestUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BookingRequestUpdateValidator implements ConstraintValidator<ValidBookingRequestUpdate, Integer> {

    private final BookingRequestRepository repository;

    @Autowired
    public BookingRequestUpdateValidator(BookingRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        if(id <= 0)
            return false;
        return repository.countUpdatableBooking(id) == 1;
    }
}
