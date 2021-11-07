package com.lasa.data.validator.model;

import com.lasa.data.model.request.FavoriteLecturerRequestModel;
import com.lasa.data.model.utils.specification.LecturerSpecification;
import com.lasa.data.repo.repository.LecturerRepository;
import com.lasa.data.validator.ValidLecturerId;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class LecturerIdValidator implements ConstraintValidator<ValidLecturerId, Integer> {
    private final LecturerRepository repository;

    @Autowired
    public LecturerIdValidator(LecturerRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Integer lecId, ConstraintValidatorContext constraintValidatorContext) {
        return repository.existsById(lecId);
    }
}
