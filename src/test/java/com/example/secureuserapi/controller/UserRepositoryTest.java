package com.example.secureuserapi.controller;

import com.example.secureuserapi.model.User;
import com.example.secureuserapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


// UserRepositoryTest
// ========================================

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    //  Kontrollera att admin användaren finns i databasen
    // ========================================
    @Test
    void shouldFindAdminUserInDatabase() {

        // Försöker hämta användare med användarnamn admin
        Optional<User> adminUser = userRepository.findByUsername("admin");

        // Kontrollera att användaren finns
        assertThat(adminUser)
                .as("Admin-användare bör finnas i testdatabasen")
                .isPresent();

        // Kontrollera att rollen är korrekt
        assertThat(adminUser.get().getRoles())
                .as("Admin-användaren ska ha rätt roll")
                .isEqualTo("ROLE_ADMIN");
    }
}
