package se.me.demo.util;


import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.me.demo.model.AppUser;
import se.me.demo.repository.AppUserRepository;

@Component
public class TestComponent {
    private final PasswordEncoder passwordEncoder;
    AppUserRepository appUserRepository;

    public TestComponent(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
    }


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
