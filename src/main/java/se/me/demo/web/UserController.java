package se.me.demo.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Denna klass är en endpoint som endast ska kunna nås av user och admin.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * Denna metod visar usersidan
     * @return ResponseEntity med HTML status och ett meddelande
     */
    @GetMapping
    public ResponseEntity<String> showUser() {
        return ResponseEntity.ok("user");
    }
}
