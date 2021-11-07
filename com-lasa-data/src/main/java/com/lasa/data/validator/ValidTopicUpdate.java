package com.lasa.data.validator;

import com.lasa.data.validator.model.TopicUpdateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Constraint(validatedBy = TopicUpdateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTopicUpdate {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
