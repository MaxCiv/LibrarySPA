package com.maxciv.library.controller;

import com.maxciv.library.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author maxim.oleynik
 * @since 12.11.2018
 */
@Controller
@Slf4j
public class MainController {

    @GetMapping("/")
    public String getHome(@AuthenticationPrincipal User user) {
        return "home";
    }
}
