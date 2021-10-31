package com.lasa.data.validator;

import com.lasa.data.validator.model.LecturerIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = LecturerIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLecturerId {
    String message() default "LECTURER_ID_NOT_VALID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
