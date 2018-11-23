package com.maxciv.library.controller;

import com.maxciv.library.model.entity.User;
import com.maxciv.library.model.repository.UserRepository;
import com.maxciv.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author maxim.oleynik
 * @since 12.11.2018
 */
@Controller
@Slf4j
public class MainController {

    private final UserService userService;
    private final UserRepository userRepository;

    public MainController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String getHome() {
        return "redirect:/app";
    }

    @GetMapping("/app")
    public String getApp() {
        return "index";
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, @RequestParam String role, @RequestParam String password2, Model model) {
        if (!password2.equals(user.getPassword())) {
            model.addAttribute("message", "Passwords not equal.");
            return "registration";
        }

        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message", "User already exists.");
            return "registration";
        }

        userService.addUser(user, role);

        return "redirect:/login";
    }
}
