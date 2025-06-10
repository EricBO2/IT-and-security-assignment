package com.example.secureuserapi.controller;

import com.example.secureuserapi.model.User;
import com.example.secureuserapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository UserRepository;

    @Test
    void shouldFindAdminUserInDatabase() {
        Optional<User> adminUser = UserRepository.findByUsername("admin");

        assertThat(adminUser).isPresent();
        assertThat(adminUser.get().getRoles()).isEqualTo("ROLE_ADMIN");
    }
}
