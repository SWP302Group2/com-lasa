package com.lasa.data.validator;

import com.lasa.data.validator.model.TopicIdsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Constraint(validatedBy = TopicIdsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTopicIds {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
