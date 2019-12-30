package com.hwx.demo.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController {
    @GetMapping("/getUser")
    public String getCurrentUsername() {
        String userMessage = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            String role = null;
            for (GrantedAuthority g : authorities) {
                role = g.getAuthority();
            }
            userMessage = userDetails.getUsername() + "   " + role;
        }
        return userMessage;
    }
}
