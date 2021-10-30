package com.lasa.data.validator.model;

import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.validator.ValidTimeStartAndTimeEnd;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.Objects;

public class TimeStartAndTimeEndValidator implements ConstraintValidator<ValidTimeStartAndTimeEnd, SlotRequestModel> {
    @Override
    public boolean isValid(SlotRequestModel slotRequestModel, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.nonNull(slotRequestModel.getTimeStart()) && Objects.nonNull(slotRequestModel.getTimeEnd())) {
            if(slotRequestModel.getTimeStart().isAfter(slotRequestModel.getTimeEnd())
            || !slotRequestModel.getTimeStart().isAfter(LocalDateTime.now().plusMinutes(50)))
                return false;

            return true;
        }

        return false;
    }
}
