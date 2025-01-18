package com.example.lab5.controller;

import com.example.lab5.model.User;
import com.example.lab5.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
    @Autowired
    private ObjectMapper objectMapper;

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
        String correctUserJson = objectMapper.writeValueAsString(correctUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(correctUserJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Lisa Carek"))
                .andExpect(jsonPath("$.email").value("lisacarek@example.com"));

        User incorrectUser = new User(null, null, "annfoster@example.com");
        String incorrectUserJson = objectMapper.writeValueAsString(incorrectUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(incorrectUserJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatesUser() throws Exception {
        User user = new User(null, "Adam Green", "adamgreen@example.com");
        String userJson = objectMapper.writeValueAsString(user);

        MvcResult result = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Adam Green"))
                .andExpect(jsonPath("$.email").value("adamgreen@example.com"))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Long userId = JsonPath.parse(response).read("$.id", Long.class);

        User userUpdate = new User(null, "John Smith", "johnsmith@example.com");
        String userUpdateJson = objectMapper.writeValueAsString(userUpdate);

        mockMvc.perform(put("/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userUpdateJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John Smith"))
                .andExpect(jsonPath("$.email").value("johnsmith@example.com"));
    }

    @Test
    void deletesUser() throws Exception {
        User user = new User(null, "Adam Walker", "adamwalker@example.com");
        String userJson = objectMapper.writeValueAsString(user);

        MvcResult result = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Adam Walker"))
                .andExpect(jsonPath("$.email").value("adamwalker@example.com"))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Long userId = JsonPath.parse(response).read("$.id", Long.class);

        mockMvc.perform(delete("/users/" + userId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isNotFound());
    }
}
