package se.me.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.me.demo.model.AppUser;
import se.me.demo.model.AppUserDTO;
import se.me.demo.repository.AppUserRepository;
import se.me.demo.util.LoggerComponent;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final DTOConverter dtoConverter;
    private final LoggerComponent logger;

    public AppUserService(AppUserRepository appUserRepository, DTOConverter dtoConverter, LoggerComponent logger) {
        this.dtoConverter = dtoConverter;
        this.appUserRepository = appUserRepository;
        this.logger = logger;

    }

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

    public ResponseEntity<String> deleteUser(String user) {
        if (appUserRepository.findByUsername(user) != null) {
            appUserRepository.delete(appUserRepository.findByUsername(user));
            logger.logWarn("User "+user+" deleted");
            return ResponseEntity.ok().body("User deleted successfully");
        }
        else{
            logger.logError("User "+user+" dose not exists");
            return ResponseEntity.badRequest().body("Username already doesn't exists");
        }
    }
}
