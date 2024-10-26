package com.example.lab1.controller;

import com.example.lab1.model.User;
import com.example.lab1.repository.UserRepository;
import com.example.lab1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "users";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("email") String email, @RequestParam("password") String password,
                               @RequestParam("first-name") String firstName, @RequestParam("last-name") String lastName,
                               @RequestParam("phone-number") String phoneNumber, @RequestParam("age") int age) {
        userService.addUser(new User(email, password, firstName, lastName, phoneNumber, age));

        return "redirect:/users";
    }
}
