package se.me.demo.web;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import se.me.demo.model.AppUser;
import se.me.demo.service.AppUserService;

@RestController
@RequestMapping("/register")
public class FormContoller {

    AppUserService appUserService;

    public FormContoller(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<String> handleForm(@Valid AppUser user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("hej i just met you");// add globel exeption handeler
        }
        return appUserService.postUser(user);
    }

    @DeleteMapping
    public ResponseEntity<String> handleDelete(String name) {
        return appUserService.deleteUser(name);
    }
}
