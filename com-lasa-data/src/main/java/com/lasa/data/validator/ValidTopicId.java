package com.lasa.data.validator;

import com.lasa.data.validator.model.TopicIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Constraint(validatedBy = TopicIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTopicId {
    String message() default "TOPIC_ID_NOT_VALID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
