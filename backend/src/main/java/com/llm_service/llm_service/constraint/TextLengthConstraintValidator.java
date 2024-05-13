package com.llm_service.llm_service.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TextLengthConstraintValidator implements ConstraintValidator<ValidTextLength, String> {

    private int min;

    @Override
    public void initialize(ValidTextLength constraintAnnotation) {
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String text, ConstraintValidatorContext context) {
        if (text == null) {
            return true;
        }
        if (text.length() < min) {
            String errorMessage = "Text length must be at least " + min + " characters";
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
            return false;
        }
        return true;
    }
}
