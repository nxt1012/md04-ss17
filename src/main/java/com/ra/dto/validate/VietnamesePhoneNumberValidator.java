package com.ra.dto.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class VietnamesePhoneNumberValidator implements ConstraintValidator<VietnamesePhoneNumberConstraint, String> {
    @Override
    public void initialize(VietnamesePhoneNumberConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO: check working Vietnamese phone number regex
        return value.matches("^(?:\\+?84|0)(3|5|7|8|9)(?:\\d{8})$");
    }
}
