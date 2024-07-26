package com.example.springldap.controller;

import com.example.springldap.model.LdapUser;
import com.example.springldap.service.LdapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LdapAuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(LdapAuthenticationController.class);
    private final LdapService ldapService;

    public LdapAuthenticationController(LdapService ldapService) {
        this.ldapService = ldapService;
    }

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

    @GetMapping("/getAllUsers")
    public List<LdapUser> getAllUsers() {
        return ldapService.getAllUsers();
    }

    @GetMapping("/getUserById/{uid}")
    public LdapUser getUserById(@PathVariable String uid) {
        return ldapService.getUserById(uid);
    }

    @GetMapping("/deleteUser/{uid}")
    public String deleteUser(@PathVariable String uid) {
        ldapService.deleteUser(uid);
        return "User Deleted";
    }
}
