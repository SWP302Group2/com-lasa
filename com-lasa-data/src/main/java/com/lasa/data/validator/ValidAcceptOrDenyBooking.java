package com.lasa.data.validator;

import com.lasa.data.validator.model.AcceptOrDenyBookingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AcceptOrDenyBookingValidator.class)
public @interface ValidAcceptOrDenyBooking {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
