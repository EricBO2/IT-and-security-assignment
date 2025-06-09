package se.me.demo.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import se.me.demo.service.AppUserService;

@Component
public class LoggerComponent {
    private static final Logger logger = LoggerFactory.getLogger(LoggerComponent.class);

    public void logError(String message) {
        logger.error(message);
    }
    public void logWarn(String message) {
        logger.warn(message);
    }
}
