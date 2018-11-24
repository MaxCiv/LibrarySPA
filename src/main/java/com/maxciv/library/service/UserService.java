package com.maxciv.library.service;

import com.maxciv.library.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author maxim.oleynik
 * @since 13.11.2018
 */
@Service
public interface UserService extends UserDetailsService {

    User addUser(User user, String role);
    User updateUser(User user, String name, String username, String password, String role);
}
