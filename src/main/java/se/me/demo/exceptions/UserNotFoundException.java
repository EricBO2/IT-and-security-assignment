package se.me.demo.exceptions;

/**
 * Detta är ett egenskapat exception som ärver från RunTimeException.
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * Denna metod visar ett felmeddelande när exceptionet aktiveras.
     * @param message Ett meddelande som visas om exceptionet aktiveras.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
