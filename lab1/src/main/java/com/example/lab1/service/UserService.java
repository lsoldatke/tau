package com.example.lab1.service;

import com.example.lab1.model.User;
import com.example.lab1.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Email and password fields are required");
        }

        if (user.getAge() < 0 || user.getAge() > 130) {
            throw new IllegalArgumentException("Age must be between 0 and 130");
        }

        return userRepository.save(user);
    }
}
