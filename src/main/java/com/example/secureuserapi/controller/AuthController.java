package com.example.secureuserapi.controller;

import com.example.secureuserapi.model.User;
import com.example.secureuserapi.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AuthController {

    // ========================================
    // Fält och konfiguration
    // ========================================

    private final UserRepository userRepository;

    // JWT-hemlighet hämtas från application.properties
    @Value("${jwt.secret}")
    private String jwtSecret;

    // Konstruktor med dependency injection
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // ========================================
    // Tar emot inloggningsuppgifter och returnerar JWT-token vid lyckad autentisering
    @PostMapping("/request-token")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        // Kontrollera att lösenord inte är tomt
        if (loginRequest.password() == null || loginRequest.password().isBlank()) {
            return ResponseEntity.badRequest().body("Password is required");
        }

        // Kontrollera användarnamn och lösenord
        return userRepository.findByUsername(loginRequest.username())
                .filter(user -> user.getPassword().equals(loginRequest.password()))
                .map(user -> ResponseEntity.ok(generateToken(user)))
                .orElseGet(() -> ResponseEntity.status(401).body("Unauthorized"));
    }


    // jwt token
    // ========================================
    private String generateToken(User user) {
        long expirationTime = 1000 * 60 * 60;

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                .compact();
    }


    // ========================================
    // Används för att ta emot inloggningsdata
    public record LoginRequest(String username, String password) {}
}
