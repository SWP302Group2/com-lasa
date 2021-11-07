package com.lasa.data.validator.model;

import com.lasa.data.repo.repository.LecturerRepository;
import com.lasa.data.validator.ValidLecturerIds;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class LecturerIdsValidator implements ConstraintValidator<ValidLecturerIds, List<Integer>> {

    private final LecturerRepository lecturerRepository;

    public LecturerIdsValidator(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public boolean isValid(List<Integer> ids, ConstraintValidatorContext constraintValidatorContext) {
        return lecturerRepository.countActiveLecturers(ids) == ids.size();
    }
}
