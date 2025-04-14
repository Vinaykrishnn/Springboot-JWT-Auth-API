package com.jwt.token.controller;

import com.jwt.token.helper.JwtUtil;
import com.jwt.token.model.JwtResponse;
import com.jwt.token.model.JwtToken;
import com.jwt.token.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public JwtController(CustomUserDetailsService customUserDetailsService,
                         JwtUtil jwtUtil,
                         AuthenticationManager authenticationManager) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtToken jwtToken) throws Exception {
        System.out.println("AuthenticationManager class = " + authenticationManager.getClass());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtToken.getUsername(), jwtToken.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad credentials");
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtToken.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
