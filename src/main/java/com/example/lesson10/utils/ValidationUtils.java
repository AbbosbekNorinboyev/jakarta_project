package com.example.lesson10.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.HashSet;
import java.util.Set;

public class ValidationUtils {
    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = validatorFactory.getValidator();

    public static <T> Set<String> validate(T object) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        Set<String> errorMessages = new HashSet<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            errorMessages.add(constraintViolation.getMessage());
        }
        return errorMessages;
    }
}
