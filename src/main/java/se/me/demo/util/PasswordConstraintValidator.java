package se.me.demo.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password,
                           ConstraintValidatorContext ctx) {

        ctx.disableDefaultConstraintViolation();

        if (password == null) {
            ctx.buildConstraintViolationWithTemplate("Please enter a password")
                    .addConstraintViolation();
            return false;
        }
        if (password.length() < 8) {
            ctx.buildConstraintViolationWithTemplate("Password must be at least 8 characters")
                    .addConstraintViolation();
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            ctx.buildConstraintViolationWithTemplate("Password must contain at least one uppercase letter")
                    .addConstraintViolation();
            return false;
        }
        if (password.replaceAll("[^0-9]", "").length() < 2) {
            ctx.buildConstraintViolationWithTemplate("Password requires at least 2 numbers")
                    .addConstraintViolation();
            return false;
        }
        if (password.replaceAll("[^!@#$%&*]", "").length() < 2) {
            ctx.buildConstraintViolationWithTemplate("Password requires at least 2 special characters")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
