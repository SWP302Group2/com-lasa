package com.lasa.data.validator.model;

import com.lasa.data.validator.ValidOneOf;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

public class OneOfValidator implements ConstraintValidator<ValidOneOf, Integer> {

    private int[] values;

    @Override
    public void initialize(ValidOneOf constraintAnnotation) {
        this.values = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if(Objects.nonNull(integer))
            return Arrays.stream(values)
                    .anyMatch(t -> integer.intValue() == t);
        return true;
    }
}
