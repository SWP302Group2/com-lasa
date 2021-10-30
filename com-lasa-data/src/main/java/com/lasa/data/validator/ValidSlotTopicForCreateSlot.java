package com.lasa.data.validator;

import com.lasa.data.validator.model.SlotCreateValidator;
import com.lasa.data.validator.model.SlotTopicForCreateSlotValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Constraint(validatedBy = SlotTopicForCreateSlotValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSlotTopicForCreateSlot {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
