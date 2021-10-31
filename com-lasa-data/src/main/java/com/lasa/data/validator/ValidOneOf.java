package com.lasa.data.validator;

import com.lasa.data.validator.model.OneOfValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OneOfValidator.class)
public @interface ValidOneOf {
    String message() default "value must match one of the values in the list";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int[] value() default {};
}
