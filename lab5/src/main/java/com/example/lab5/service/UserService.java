package com.example.lab5.service;

import com.example.lab5.model.User;
import com.example.lab5.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with given ID does not exist."));

        userRepository.deleteById(id);
    }

    public User updateById(Long id, User userUpdate) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User with given ID doesn't exist."));

        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());

        return userRepository.save(user);
    }

    public User add(User user) {
        return userRepository.save(user);
    }
}
