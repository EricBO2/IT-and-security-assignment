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

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private final String jwt = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
            + "(byt mot riktig token)";


    @Test
    void testRegisterUserWithValidJwtToken() throws Exception {
        var newUser = new RegisterRequest("testuser", "Test123!", "USER");

        mockMvc.perform(post("/register")
                        .header(HttpHeaders.AUTHORIZATION, jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated()); // 201
    }


    @Test
    void testDeleteUserWithValidJwtToken() throws Exception {
        mockMvc.perform(delete("/users/1")
                        .header(HttpHeaders.AUTHORIZATION, jwt))
                .andExpect(status().isNoContent()); // 204
    }


    record RegisterRequest(String username, String password, String role) {}
}
