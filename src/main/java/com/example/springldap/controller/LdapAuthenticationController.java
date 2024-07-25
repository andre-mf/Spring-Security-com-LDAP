package com.example.springldap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LdapAuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(LdapAuthenticationController.class);

    @GetMapping("/")
    public String index() {
        return "Welcome to the home page!";
    }
}
