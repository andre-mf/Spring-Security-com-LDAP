package com.example.springldap.service;

import com.example.springldap.model.LdapUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

@Service
public class LdapService {

    @Autowired
    private LdapTemplate ldapTemplate;
    private static final String BASE_DN="cn=users";

    public void addUser(LdapUser ldapUser) {
        ldapTemplate.bind("cn=" + ldapUser.getUsername() + "," + BASE_DN, null, ldapUser.toAttributes());
    }
}
