package com.example.secureuserapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    // ========================================
    // Beroenden för testning
    // ========================================

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    // Hämta JWT med inloggning
    // ========================================
    private String getJwtToken() throws Exception {
        var login = new LoginRequest("admin", "Password!2");

        var result = mockMvc.perform(post("/request-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andReturn();

        return "Bearer " + result.getResponse().getContentAsString();
    }


    // Registrera ny användare med giltig JWT
    // ========================================
    @Test
    void testRegisterUserWithValidJwtToken() throws Exception {
        var newUser = new RegisterRequest("testuser", "Test123!", "USER");

        mockMvc.perform(post("/register")
                        .header(HttpHeaders.AUTHORIZATION, getJwtToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated());
    }


    //  Ta bort användare med giltig JWT
    // ========================================
    @Test
    void testDeleteUserWithValidJwtToken() throws Exception {
        mockMvc.perform(delete("/users/1")
                        .header(HttpHeaders.AUTHORIZATION, getJwtToken()))
                .andExpect(status().isNoContent());
    }

    // LoginRequest och RegisterRequest
    // ========================================
    record LoginRequest(String username, String password) {}
    record RegisterRequest(String username, String password, String role) {}
}
