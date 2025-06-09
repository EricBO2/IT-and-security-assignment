package se.me.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.me.demo.model.AppUser;
import se.me.demo.model.AppUserDTO;

@Service
public class DTOConverter {

    private PasswordEncoder passwordEncoder;

    public DTOConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //Omvandling mellan DTO och domänklass
    public void save(AppUserDTO userDTO) {
        AppUser appUser = toAppuser(userDTO);
    }

    //Konvertering-metoder
    public AppUser toAppuser(AppUserDTO userDTO) {
        AppUser appUser = new AppUser();
        appUser.setUsername(userDTO.getUsername());
        appUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        appUser.setRole(userDTO.getRole());
        return appUser;
    }

    public AppUserDTO toDTO(AppUser appUser) {
        AppUserDTO userDTO = new AppUserDTO();
        userDTO.setUsername(appUser.getUsername());
        userDTO.setPassword(appUser.getPassword());
        userDTO.setRole(appUser.getRole());
        return userDTO;
    }
}
