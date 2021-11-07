package com.lasa.data.validator;

import com.lasa.data.validator.model.LecturerIdsValidator;
import com.lasa.data.validator.model.MajorIdsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MajorIdsValidator.class)
public @interface ValidMajorId {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
