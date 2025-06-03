package se.me.demo.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            System.out.println("Please enter password");
            return false;
        }

        if (password.length() <= 8) {
            System.out.println("Password must be at least 8 characters long");
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter");
            return false;
        }

        if (password.replaceAll("[^0-9]", "").length() <= 2) {
            System.out.println("Password must contain at least two digits");
            return false;
        }

        if (password.replaceAll("[^!@#$%&*]", "").length() <= 2) {
            System.out.println("Password must contain at least two special character");
            return false;
        }

        return true;
    }

}
