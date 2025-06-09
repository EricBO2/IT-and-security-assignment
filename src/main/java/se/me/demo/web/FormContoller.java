package se.me.demo.web;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.me.demo.model.AppUserDTO;
import se.me.demo.service.AppUserService;

@RestController
@RequestMapping("/register")
public class FormContoller {

    AppUserService appUserService;

    public FormContoller(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<String> handleForm(@Valid AppUserDTO user) {
        return appUserService.postUser(user);
    }

    @DeleteMapping
    public ResponseEntity<String> handleDelete(String name) {
        return appUserService.deleteUser(name);
    }
}
