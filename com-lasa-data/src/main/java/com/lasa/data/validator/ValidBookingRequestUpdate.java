package com.lasa.data.validator;

import com.lasa.data.validator.model.BookingRequestUpdateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = BookingRequestUpdateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBookingRequestUpdate {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
