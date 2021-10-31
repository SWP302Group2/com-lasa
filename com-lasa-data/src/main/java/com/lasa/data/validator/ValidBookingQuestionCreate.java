package com.lasa.data.validator;

import com.lasa.data.validator.model.BookingQuestionCreateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BookingQuestionCreateValidator.class)
@Target(ElementType.TYPE)
public @interface ValidBookingQuestionCreate {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
