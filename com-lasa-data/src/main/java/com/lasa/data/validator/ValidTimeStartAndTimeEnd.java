package com.lasa.data.validator;

import com.lasa.data.validator.model.SlotIdValidator;
import com.lasa.data.validator.model.TimeStartAndTimeEndValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Constraint(validatedBy = TimeStartAndTimeEndValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTimeStartAndTimeEnd {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
