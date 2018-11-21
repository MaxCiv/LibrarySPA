package com.maxciv.library.controller;

import com.maxciv.library.model.entity.User;
import com.maxciv.library.model.exception.LibraryAppException;
import com.maxciv.library.model.repository.UserRepository;
import com.maxciv.library.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maxim.oleynik
 * @since 18.11.2018
 */
@RestController
@RequestMapping("api/user")
public class UserController {


    @GetMapping("current-data")
    public Map<String, Object> findAll(@AuthenticationPrincipal User user) {
        Map<String, Object> frontendData = new HashMap<>();
        frontendData.put("currentUser", user);
        frontendData.put("currentPage", 1);

        return frontendData;
    }

    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Autowired


    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable("id") User user) {
        return user;
    }

    @PostMapping
    public User addUser(User user, @RequestParam String role) throws LibraryAppException {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            throw new LibraryAppException("Login already in use");
        }

        return userService.addUser(user, role);
    }

    @PutMapping("{id}/edit")
    public User postEditUser(
            @PathVariable("id") User user,
            @RequestParam String name,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role
    ) throws LibraryAppException {
        User userFromDb = userRepository.findByUsername(username);
        if (userFromDb != null && !user.getUsername().equals(username)) {
            throw new LibraryAppException("Login already in use");
        }

        return userService.updateUser(user, name, username, password, role);
    }
}
