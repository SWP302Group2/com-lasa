package com.lasa.data.validator.model;

import com.lasa.data.model.request.SlotRequestModel;
import com.lasa.data.repo.repository.SlotRepository;
import com.lasa.data.validator.ValidSlotUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SlotUpdateValidator implements ConstraintValidator<ValidSlotUpdate, SlotRequestModel> {

    private final SlotRepository slotRepository;

    @Autowired
    public SlotUpdateValidator(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Override
    public boolean isValid(SlotRequestModel slotRequestModel, ConstraintValidatorContext constraintValidatorContext) {
        return slotRepository.countActiveSlot(slotRequestModel.getId()) == 1;
    }
}
