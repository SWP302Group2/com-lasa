package com.lasa.data.validator;

import com.lasa.data.validator.model.SlotIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = SlotIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSlotId {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
