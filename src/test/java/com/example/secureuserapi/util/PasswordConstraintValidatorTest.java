package se.me.demo;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.me.demo.util.PasswordConstraintValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class PasswordConstraintValidatorTest {

    // ============================================================
    // Instansiering av validerare och Mock-objekt
    // ============================================================
    @Test
    @DisplayName("Lösenordsvalidering – olika fall testas")
    public void testPasswordValidation_multipleCases() {
        // Skapa valideraren som testas
        PasswordConstraintValidator validator = new PasswordConstraintValidator();

        // Mocka ConstraintValidatorContext och dess builder
        ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
        ConstraintValidatorContext.ConstraintViolationBuilder builder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(builder);
        when(builder.addConstraintViolation()).thenReturn(context);

        // ============================================================
        // Ogiltiga lösenord som bryter mot olika regler
        // ============================================================

        // För kort (mindre än 8 tecken)
        assertFalse(validator.isValid("Ab1!", context), "För kort lösenord borde vara ogiltigt");

        // Saknar versaler (stora bokstäver)
        assertFalse(validator.isValid("abc!12@3", context), "Lösenord utan versal borde vara ogiltigt");

        // Saknar siffror
        assertFalse(validator.isValid("Abc!@xyz", context), "Lösenord utan siffror borde vara ogiltigt");

        // Saknar specialtecken
        assertFalse(validator.isValid("Abc12345", context), "Lösenord utan specialtecken borde vara ogiltigt");

        // ============================================================
        // Giltigt lösenord som uppfyller alla krav
        // ============================================================
        assertTrue(validator.isValid("Abc!2@3xyz", context), "Korrekt format borde vara giltigt");
    }
}
