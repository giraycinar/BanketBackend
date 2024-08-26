package com.bilgeadam.banket.annotation;

import com.bilgeadam.banket.constant.ValidationRegex;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.UUID;

public class UUIDValidator implements ConstraintValidator<UUIDValidation, UUID> {

    @Override
    public void initialize(UUIDValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext context) {
        return uuid.toString().matches(ValidationRegex.UUID_REGEX);
    }

}
