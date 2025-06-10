package se.me.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.me.demo.model.AppUser;
import se.me.demo.model.AppUserDTO;

/**
 * Denna klass konverterar AppUser till AppUserDTO och omvänt
 */
@Service
public class DTOConverter {

    private PasswordEncoder passwordEncoder;

    /**
     * En konstruktor som injectar passwordEncoder
     * @param passwordEncoder Representerar PasswordEncoder.
     */
    public DTOConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Denna metod tar in en AppUserDTO som omvandlar den till en AppUser.
     * @param userDTO Representerar användar-DTO:t som man vill omvandla till
     *        Appuser.
     * @return En AppUser.
     */
    public AppUser toAppuser(AppUserDTO userDTO) {
        AppUser appUser = new AppUser();
        appUser.setUsername(userDTO.getUsername());
        appUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        appUser.setRole(userDTO.getRole());
        appUser.setConsentGiven(userDTO.getConsentGiven());
        return appUser;
    }

    /**
     * Denna metod tar in en AppUser som omvandlar den till en AppUserDTO.
     * @param appUser Representerar den Appuser som man vill omvandla till
     *        AppUserDTO.
     * @return En AppUserDTO.
     */
    public AppUserDTO toDTO(AppUser appUser) {
        AppUserDTO userDTO = new AppUserDTO();
        userDTO.setUsername(appUser.getUsername());
        userDTO.setPassword(appUser.getPassword());
        userDTO.setRole(appUser.getRole());
        userDTO.setConsentGiven(appUser.getConsentGiven());
        return userDTO;
    }
}
