package com.maxciv.library.controller;

import com.maxciv.library.model.entity.User;
import com.maxciv.library.model.repository.UserRepository;
import com.maxciv.library.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author maxim.oleynik
 * @since 11.11.2018
 */
@Controller
@Slf4j
@RequestMapping("/librarian")
@PreAuthorize("hasAuthority('LIBRARIAN')")
public class LibrarianController {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    @Autowired
    public LibrarianController(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/tableUsers")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "tableUsers";
    }

    @GetMapping("/editUser/{user}")
    public String getEditUser(@PathVariable User user, Model model) {
        model.addAttribute("userForEdit", user);
        return "editProfile";
    }

    @PostMapping("/editUser/{user}")
    public String postEditUser(
            @PathVariable User user,
            @RequestParam String name,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String password2,
            @RequestParam String role,
            Model model
    ) {
        if (!password.equals(password2)) {
            model.addAttribute("message", "Passwords not equal.");
            model.addAttribute("userForEdit", user);
            return "editProfile";
        }

        userService.updateUser(user, name, username, password, role);
        return "redirect:/librarian/tableUsers";
    }
}
