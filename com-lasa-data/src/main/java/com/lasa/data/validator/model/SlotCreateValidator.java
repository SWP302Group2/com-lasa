package com.lasa.data.validator.model;

import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.repo.repository.SlotRepository;
import com.lasa.data.validator.ValidSlotCreate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class SlotCreateValidator implements ConstraintValidator<ValidSlotCreate, SlotRequestModel> {

    private final SlotRepository slotRepository;

    @Autowired
    public SlotCreateValidator(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Override
    public boolean isValid(SlotRequestModel slotRequestModel, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.nonNull(slotRequestModel.getTimeStart())
        && Objects.nonNull(slotRequestModel.getTimeEnd())
        && Objects.nonNull(slotRequestModel.getLecturerId())) {
            return slotRepository.countActiveSlotByTimeStartAndTimeEndAndLecturerId(
                    slotRequestModel.getTimeStart(),
                    slotRequestModel.getTimeEnd(),
                    slotRequestModel.getLecturerId()) == 0;
        }

        return false;
    }
}
