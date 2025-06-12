package se.me.demo.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Denna klass är en endpoint som endast ska kunna nås av alla.
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    /**
     * Denna metod visar startsidan
     * @return ResponseEntity med HTML status och ett meddelande
     */
    @GetMapping
    public ResponseEntity<String> showHome() {

        return ResponseEntity.ok("This is the home page");
    }
}
