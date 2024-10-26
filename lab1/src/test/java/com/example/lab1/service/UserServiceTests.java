package com.example.lab1.service;

import com.example.lab1.model.User;
import com.example.lab1.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserService.class)
public class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveUserWithValidData() {
        User user = new User("abc@def.com", "a1b2c3d4e5", "John", "Doe",
                "123456789", 25);

        User savedUser = userService.addUser(user);

        assertNotNull(savedUser);
        assertEquals("abc@def.com", savedUser.getEmail());
        assertEquals("a1b2c3d4e5", savedUser.getPassword());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());
        assertEquals("123456789", savedUser.getPhoneNumber());
        assertEquals(25, savedUser.getAge());
    }

    @Test
    public void shouldNotSaveUserWithoutRequiredData() {
        User user = new User("", "", "John", "Doe",
                "123456789", 25);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.addUser(user));
        User savedUser = userService.addUser(user);

        assertEquals("Email and password fields are required", exception.getMessage());
        assertNull(savedUser);
    }

    @Test
    public void shouldNotSaveUserWithIncorrectAge() {
        User user = new User("abc@def.com", "a1b2c3d4e5", "John", "Doe",
                "123456789", -45);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.addUser(user));
        User savedUser = userService.addUser(user);

        assertEquals("Age must be between 0 and 130", exception.getMessage());
        assertFalse(savedUser.getAge() < 0 || user.getAge() > 130);
    }
}
