package com.lasa.data.validator;

import com.lasa.data.validator.model.SlotUpdateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Constraint(validatedBy = SlotUpdateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSlotUpdate {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
