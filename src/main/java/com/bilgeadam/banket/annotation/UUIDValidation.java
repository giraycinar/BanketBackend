package com.bilgeadam.banket.annotation;

import com.bilgeadam.banket.constant.ValidationMessage;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UUIDValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UUIDValidation {

    String message() default ValidationMessage.UUID_REGEX_ERROR_MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
