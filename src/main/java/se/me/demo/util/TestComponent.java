package se.me.demo.util;


import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.me.demo.model.AppUser;
import se.me.demo.repository.AppUserRepository;

/**
 * Denna klass ser till att en AppUser finns vid start och skapar en vid behov.
 */
@Component
public class TestComponent {
    private final PasswordEncoder passwordEncoder;
    AppUserRepository appUserRepository;

    /**
     * Denna konstruktor injectar samtliga parametrar
     * @param passwordEncoder
     * @param appUserRepository
     */
    public TestComponent(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
    }


    /**
     * Denna metod körs en gång när man startat programmet (genom @PostConstruct) och
     * ifall det inte finns en APP_USER som heter user så läggs en AppUser till i databasen.
     */
    @PostConstruct
    public void initialize() {
        if(appUserRepository.findByUsername("user")==null) {
            AppUser user = new AppUser();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("ADMIN");
            user.setConsentGiven(true);
            appUserRepository.save(user);

        }
    }
}
