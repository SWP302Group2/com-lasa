package com.lasa.data.validator;

import com.lasa.data.validator.model.BookingRequestDeleteValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = BookingRequestDeleteValidator.class)
public @interface ValidBookingQuestionDelete {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
