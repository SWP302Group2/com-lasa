package com.lasa.data.validator;

import com.lasa.data.validator.model.QuestionUpdateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Constraint(validatedBy = QuestionUpdateValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidQuestionUpdate {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
