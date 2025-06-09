package se.me.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.me.demo.model.AppUser;
import se.me.demo.model.AppUserDTO;
import se.me.demo.repository.AppUserRepository;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final DTOConverter dtoConverter;

    public AppUserService(AppUserRepository appUserRepository, DTOConverter dtoConverter) {
        this.dtoConverter = dtoConverter;
        this.appUserRepository = appUserRepository;
    }

    public ResponseEntity<String> postUser(AppUserDTO userDTO) {
        if (appUserRepository.findByUsername(userDTO.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        else{
            AppUser user = dtoConverter.toAppuser(userDTO);
            appUserRepository.save(user);
            return ResponseEntity.ok().body("User added successfully");
        }
    }

    public ResponseEntity<String> deleteUser(String user) {
        if (appUserRepository.findByUsername(user) != null) {
            appUserRepository.delete(appUserRepository.findByUsername(user));
            return ResponseEntity.ok().body("User deleted successfully");
        }
        else{
            return ResponseEntity.badRequest().body("Username already doesn't exists");
        }
    }
}
