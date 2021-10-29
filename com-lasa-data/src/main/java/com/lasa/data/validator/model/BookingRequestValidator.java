package com.lasa.data.validator.model;

import com.lasa.data.model.request.BookingRequestRequestModel;
import com.lasa.data.repo.repository.BookingRequestRepository;
import com.lasa.data.validator.ValidBookingRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BookingRequestValidator implements ConstraintValidator<ValidBookingRequest, BookingRequestRequestModel> {
    private final BookingRequestRepository repository;

    @Autowired
    public BookingRequestValidator(BookingRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(BookingRequestRequestModel bookingRequestRequestModel, ConstraintValidatorContext constraintValidatorContext) {

        return repository.countByStudentIdAndSlotId(bookingRequestRequestModel.getStudentId(), bookingRequestRequestModel.getSlotId()) == 0;
    }
}
