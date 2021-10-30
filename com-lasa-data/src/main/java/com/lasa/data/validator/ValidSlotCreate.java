package com.lasa.data.validator;

import com.lasa.data.validator.model.SlotCreateValidator;
import com.lasa.data.validator.model.TimeStartAndTimeEndValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE_USE})
@Constraint(validatedBy = SlotCreateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSlotCreate {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
