package com.ra.dto.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VietnamesePhoneNumberValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VietnamesePhoneNumberConstraint {
    String message() default "Please enter a valid Vietnamese phone number starting with either '+84' or '0', followed by 9 or 10 digits.";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
