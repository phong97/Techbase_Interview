package com.challenge.techbase.controller;

import com.challenge.techbase.Service.UserServiceTest;
import com.challenge.techbase.models.dto.req.LoginRequest;
import com.challenge.techbase.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("POST /api/v1/auth/login fail because email not found")
    void testLoginFailByEmailFound() throws Exception {
        when(userService.findByEmail(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(generateLoginRequest()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/v1/auth/login fail because wrong password")
    void testLoginFailByWrongPassword() throws Exception {
        String password = bCryptPasswordEncoder.encode("12345");
        when(userService.findByEmail(anyString()))
                .thenReturn(Optional.of(UserServiceTest.generateUser(password)));

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(generateLoginRequest()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/v1/auth/login success")
    void testLoginSuccess() throws Exception {
        String password = bCryptPasswordEncoder.encode("password");
        when(userService.findByEmail(anyString()))
                .thenReturn(Optional.of(UserServiceTest.generateUser(password)));

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(generateLoginRequest()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    private String generateLoginRequest() {
        try {
            LoginRequest req = new LoginRequest();
            req.setEmail("test@gmail.com");
            req.setPassword("password");
            return mapper.writeValueAsString(req);
        } catch (JsonProcessingException e) {
        }
        return "{}";
    }

}
