package se.me.demo;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "jwt.secret=0123456789ABCDEF0123456789ABCDEF")
public class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //  TEST: Registrering med param – OK
    @Test
    @DisplayName("Registrering – lyckas med param")
    void testSuccessfulRegistrationWithParams() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "newuser")
                        .param("password", "securePassword!1")
                        .param("email", "user@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    // TEST: Inloggning med param  OK, JWT returneras
    @Test
    @DisplayName("Inloggning – lyckas med param och ger JWT")
    void testLoginWithParamsReturnsJwt() throws Exception {
        // Registrera först
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "jwtuser")
                        .param("password", "Password!2"))
                .andExpect(status().isOk());

        // Logga sedan in
        mockMvc.perform(post("/request-token")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "jwtuser")
                        .param("password", "Password!2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(".")));
    }
}
