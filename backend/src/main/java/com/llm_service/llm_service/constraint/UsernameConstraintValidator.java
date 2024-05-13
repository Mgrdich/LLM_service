package com.llm_service.llm_service.constraint;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class UsernameConstraintValidator implements ConstraintValidator<ValidUsername, String> {
    @Override
    public void initialize(ValidUsername constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        Set<String> errorMessages = new HashSet<>();

        if (!value.matches("^[A-Za-z].*")) {
            errorMessages.add("Username must start with a letter.");
        }

        if (!value.matches(".{8,30}")) {
            errorMessages.add("Username must be between 8 and 30 characters long.");
        }

        if (!value.matches("[A-Za-z0-9_]*")) {
            errorMessages.add("Username must consist of letters, digits, or underscores only.");
        }

        if (!errorMessages.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.join(" ", errorMessages))
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
