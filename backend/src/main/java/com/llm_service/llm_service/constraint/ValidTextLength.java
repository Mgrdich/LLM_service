package com.llm_service.llm_service.constraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = TextLengthConstraintValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidTextLength {

    String message() default "Text length must be at least {min} characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 5;
}
