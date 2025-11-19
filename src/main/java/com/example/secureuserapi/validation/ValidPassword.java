package com.example.secureuserapi.validation;

import se.me.demo.util.PasswordConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "Lösenordet måste vara minst 8 tecken och innehålla minst en versal, en siffra och ett specialtecken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
