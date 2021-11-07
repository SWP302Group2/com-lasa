package com.lasa.data.validator.model;

import com.lasa.data.repo.repository.MajorRepository;
import com.lasa.data.validator.ValidMajorId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MajorIdsValidator implements ConstraintValidator<ValidMajorId, String> {

    private final MajorRepository majorRepository;

    public MajorIdsValidator(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    @Override
    public boolean isValid(Integer String, ConstraintValidatorContext constraintValidatorContext) {
        return majorRepository.existsById(String);
    }
}
