package com.lasa.data.validator.model;

import com.lasa.data.repo.repository.SlotRepository;
import com.lasa.data.validator.ValidSlotId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SlotIdValidator implements ConstraintValidator<ValidSlotId, Integer> {
    private final SlotRepository slotRepository;

    @Autowired
    public SlotIdValidator(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return slotRepository.countActiveSlot(id) == 1;
    }
}
