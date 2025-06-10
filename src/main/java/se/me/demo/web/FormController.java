package se.me.demo.web;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.me.demo.model.AppUserDTO;
import se.me.demo.service.AppUserService;

/**
 * Denna klass är en endpoint som används för att interagera med databasen.
 */
@RestController
@RequestMapping("/register")
public class FormController {

    AppUserService appUserService;

    /**
     * Denna konstruktor injectar samtliga parametrar.
     * @param appUserService
     */
    public FormController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * Denna metod tar in informationen och förfrågar om att sätta in informationen i databasen.
     * @param user Representerar en användare.
     * @return En ResponseEntity med en HTTP status och har ett meddelande i sig.
     */
    @PostMapping
    public ResponseEntity<String> handleForm(@Valid AppUserDTO user) {
        return appUserService.postUser(user);
    }

    /**
     * Denna metod gör förfrågan om att radera en användare i databasen.
     * @param name Representerar namnet på det önskade kontot.
     * @return En ResponseEntity med en HTTP status och har ett meddelande i sig.
     */
    @DeleteMapping
    public ResponseEntity<String> handleDelete(String name) {
        return appUserService.deleteUser(name);
    }
}
