package com.llm_service.llm_service.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TitleConstraintValidator implements ConstraintValidator<ValidTitle, String> {

    private int min;
    private int max;

    @Override
    public void initialize(ValidTitle constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        if (title == null) {
            return true;
        }

        int length = title.length();
        if (length < min || length > max) {
            String errorMessage = "Title length must be between " + min + " and " + max + " characters";
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
            return false;
        }

        return true;
    }
}
