package com.lasa.data.validator;

import com.lasa.data.validator.model.BookingRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Constraint(validatedBy = BookingRequestValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBookingRequest {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
