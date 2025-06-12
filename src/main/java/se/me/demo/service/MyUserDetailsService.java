package se.me.demo.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import se.me.demo.exceptions.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.me.demo.model.AppUser;
import se.me.demo.repository.AppUserRepository;

import java.util.List;

/**
 * Denna klass implementerar från interfacet UserDetailsService. Denna klass används för att
 * hämta användare.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * Denna konstruktor injectar samtliga parametrar.
     * @param appUserRepository
     * @param passwordEncoder
     */
    public MyUserDetailsService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Denna metod hämtar önskad användare.
     * @param username Representerar användaren man vill hämta.
     * @return Skickar information om användaren.
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException(username + " not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,true,true,true,
                List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole().toUpperCase()))
        );
    }
}
