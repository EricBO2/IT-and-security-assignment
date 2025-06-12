package com.example.secureuserapi;

import com.example.secureuserapi.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestSecurityConfig.class)
class SecureUserApiApplicationTests {

    @Test
    void contextLoads() {
    }
}
