package se.me.demo.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.me.demo.model.AppUser;
import se.me.demo.model.AppUserDTO;
import se.me.demo.repository.AppUserRepository;
import se.me.demo.util.LoggerComponent;

/**
 * Denna klass tar information från FormController och innehåller logiken för att
 * göra ändringar via AppUserRepository. Den loggar även all aktivitet som sker i databasen
 * och även errors.
 */
@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final DTOConverter dtoConverter;
    private final LoggerComponent logger;

    /**
     * Instanserar samtliga parametrar.
     * @param appUserRepository
     * @param dtoConverter
     * @param logger
     */
    public AppUserService(AppUserRepository appUserRepository, DTOConverter dtoConverter, LoggerComponent logger) {
        this.dtoConverter = dtoConverter;
        this.appUserRepository = appUserRepository;
        this.logger = logger;

    }

    /**
     * Denna metod undersöker om det inte redan finns en user med samma namn som en annan user.
     * Sedan omvandlar DTOConverter userDTO till appUser som skickas till AppUserRepository
     * som sist sparas i databasen. Med andra ord så har en användare lagts till i databasen.
     * Den loggar även information och errors.
     * @param userDTO Den innehåller informationen om usern som ska sparas i databasen.
     * @return En ResponseEntity som innehåller HTTP-status och ett meddelande.
     */
    public ResponseEntity<String> postUser(AppUserDTO userDTO) {
        if (appUserRepository.findByUsername(userDTO.getUsername()) != null) {
            logger.logError("Username "+userDTO.getUsername()+" already exists");
            return ResponseEntity.badRequest().body("Username already exists");
        }
        else{
            AppUser user = dtoConverter.toAppuser(userDTO);
            appUserRepository.save(user);
            logger.logWarn("User "+userDTO.getUsername()+" created");
            return ResponseEntity.ok().body("User added successfully");
        }
    }

    /**
     * Denna metod raderar användare från databasen baserat på användarnamn.
     * @param user Innehåller användarnamnet till den användare som man vill radera från databasen.
     * @return En ResponseEntity som innehåller HTTP-status och ett felmeddelande.
     */
    public ResponseEntity<String> deleteUser(String user) {
        if (appUserRepository.findByUsername(user) != null) {
            appUserRepository.delete(appUserRepository.findByUsername(user));
            logger.logWarn("User "+user+" deleted");
            return ResponseEntity.ok().body("User deleted successfully");
        }
        else{
            logger.logError("User "+user+" dose not exists");
            return ResponseEntity.badRequest().body("Username doesn't exist");
        }
    }
}
