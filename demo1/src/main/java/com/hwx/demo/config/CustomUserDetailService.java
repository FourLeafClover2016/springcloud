package com.hwx.demo.config;

import com.hwx.demo.entity.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("user".equals(username)) {
            return new SecurityUser("user", "passwd", "user-role");

        } else if ("admin".equals(username)) {
            return new SecurityUser("admin", "passwd", "admin-role");
        } else {
            return null;
        }
    }
}
