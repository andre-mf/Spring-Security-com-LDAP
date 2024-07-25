package com.example.springldap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdapAuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(LdapAuthenticationController.class);

    @GetMapping("/")
    public String index() {
        return "Welcome to the home page!";
    }

    @GetMapping("/getUserDetails")
    public String getUserDetails(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // access user details
        String userName = userDetails.getUsername();
        boolean accNonExpired = userDetails.isAccountNonExpired();
        return "UserDetails: " + userName + "\n Account Non Expired: " + accNonExpired;
    }
}
