package com.lasa.data.validator.model;

import com.lasa.data.model.request.SlotBookingRequestModel;
import com.lasa.data.repo.repository.SlotRepository;
import com.lasa.data.validator.ValidAcceptOrDenyBooking;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class AcceptOrDenyBookingValidator implements ConstraintValidator<ValidAcceptOrDenyBooking, SlotBookingRequestModel> {

    private final SlotRepository slotRepository;

    @Autowired
    public AcceptOrDenyBookingValidator(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }


    @Override
    public boolean isValid(SlotBookingRequestModel model, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.isNull(model.getBookingId()) || Objects.isNull(model.getStatus()) || Objects.isNull(model.getSlotId()))
            return false;

        return slotRepository.countAvailableUpdateBookingOfSlot(model.getSlotId(), model.getLecturerId(), model.getBookingId()) == 1;

    }
}
