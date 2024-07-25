package com.example.springldap.controller;

import com.example.springldap.model.LdapUser;
import com.example.springldap.service.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private LdapService ldapService;

    @GetMapping("/addUserForm")
    public String addUserForm(Model model) {
        model.addAttribute("ldapUser", new LdapUser());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addUser(LdapUser ldapUser) {
        ldapService.addUser(ldapUser);
        return "success";
    }
}
