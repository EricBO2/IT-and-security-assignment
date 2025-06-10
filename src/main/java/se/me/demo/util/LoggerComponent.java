package se.me.demo.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Denna klass kallar på Logger som loggar händelser i databasen
 */
@Component
public class LoggerComponent {
    private static final Logger logger = LoggerFactory.getLogger(LoggerComponent.class);

    /**
     * Denna metod loggar errors.
     * @param message Ett meddelande som skrivs in i loggen vid fel.
     */
    public void logError(String message) {
        logger.error(message);
    }

    /**
     * Denna metod loggar när processer går som önskat.
     * @param message Ett meddelande som skrivs in i loggen när något händer i databasen (ej vid fel)
     */
    public void logWarn(String message) {
        logger.warn(message);
    }
}
