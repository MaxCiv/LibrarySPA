package com.maxciv.library.service;

import com.maxciv.library.model.Role;
import com.maxciv.library.model.entity.User;
import com.maxciv.library.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author maxim.oleynik
 * @since 13.11.2018
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public void addUser(User user, String role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(new HashSet<>(Arrays.asList(Role.COMMON_USER, Role.valueOfString(role))));
        userRepository.save(user);
    }

    public void updateUser(User user, String name, String username, String password, String role) {
        user.setName(name);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        user.setRoles(new HashSet<>(Arrays.asList(Role.COMMON_USER, Role.valueOfString(role))));
        userRepository.save(user);
    }
}
