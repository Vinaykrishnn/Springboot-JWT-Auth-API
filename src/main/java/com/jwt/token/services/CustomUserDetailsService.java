package com.jwt.token.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("Vinay")) {
            return User.builder()
                    .username("Vinay")
                    .password(passwordEncoder().encode("Vinay@123")) // important: password must be encoded
                    .roles("USER") // can be "ADMIN", etc.
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

}
