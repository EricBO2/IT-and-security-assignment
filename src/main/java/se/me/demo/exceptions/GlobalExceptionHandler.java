package se.me.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;


/**
 * Denna klass hanterar exceptions kring hela applikationen.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Denna metod hanterar exceptions som kan förekomma vid registrering av user.
     * @param ex Denna parameter representerar de exception som har uppstått.
     * @return En ResponseEntity som innehåller HTTP-status och felmeddelanden.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage(),
                        (existing, replacement) -> existing));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Hanterar UserNotFoundException
     * @param ex Denna parameter representerar UserNotFoundException.
     * @return En ResponseEntity som innehåller HTTP-status och ett felmeddelande.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
