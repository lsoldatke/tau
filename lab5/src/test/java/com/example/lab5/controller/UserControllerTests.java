package com.example.lab5.controller;

import com.example.lab5.model.User;
import com.example.lab5.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        userRepository.saveAll(List.of(
                new User(null, "John Doe", "johndoe@example.com"),
                new User(null, "Ben Smith", "bensmith@example.com")
        ));
    }

    @Test
    void returnsAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(
                        MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("johndoe@example.com"))
                .andExpect(jsonPath("$[1].name").value("Ben Smith"))
                .andExpect(jsonPath("$[1].email").value("bensmith@example.com"));
    }

    @Test
    void returnsUser() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"));

        mockMvc.perform(get("/users/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createsUser() throws Exception {
        User correctUser = new User(null, "Lisa Carek", "lisacarek@example.com");

        mockMvc.perform(post("/users"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.name").value("Lisa Carek"))
                .andExpect(jsonPath("$.email").value("lisacarek@example.com"));

        User incorrectUser = new User(null, null, "annfoster@example.com");

        mockMvc.perform(post("/users"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void updatesUser() throws Exception {
        User userUpdate = new User(null, "John Smith", "johnsmith@example.com");

        mockMvc.perform(put("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.name").value("John Smith"))
                .andExpect(jsonPath("$.email").value("johnsmith@example.com"));
    }

    @Test
    void deletesUser() throws Exception {
        mockMvc.perform(delete("/users/2"))
                .andExpect(status().isNoContent())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));

        mockMvc.perform(get("/users/2"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));
    }
}
