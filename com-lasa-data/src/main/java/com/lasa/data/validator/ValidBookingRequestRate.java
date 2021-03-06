package com.lasa.data.validator;

import com.lasa.data.validator.model.BookingRequestRateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Constraint(validatedBy = BookingRequestRateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBookingRequestRate {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
