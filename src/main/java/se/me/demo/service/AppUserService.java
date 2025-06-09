package se.me.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.me.demo.model.AppUser;
import se.me.demo.repository.AppUserRepository;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public ResponseEntity<String> postUser(AppUser user) {
        if (appUserRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        else{
            appUserRepository.save(user);
            return ResponseEntity.ok().body("User added successfully");
        }
    }

    public ResponseEntity<String> deleteUser(String user) {
        if (appUserRepository.findByUsername(user) == null) {
            appUserRepository.delete(appUserRepository.findByUsername(user));
            return ResponseEntity.ok().body("User deleted successfully");
        }
        else{
            return ResponseEntity.badRequest().body("Username already doesn't exists");
        }
    }
}
