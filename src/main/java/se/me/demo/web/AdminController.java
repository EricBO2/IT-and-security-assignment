package se.me.demo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Denna klass är en endpoint som endast ska kunna nås av en admin.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    /**
     * Denna metod visar admin sidan
     * @return ResponseEntity med HTML status och ett meddelande
     */
    @GetMapping
    public ResponseEntity<String> showAdminPage() {

        return ResponseEntity.ok("Welcome admin");
    }
}
