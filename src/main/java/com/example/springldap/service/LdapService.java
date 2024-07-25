package com.example.springldap.service;

import com.example.springldap.model.LdapUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LdapService {

    @Autowired
    private LdapTemplate ldapTemplate;
    private static final String BASE_DN="cn=users";

    public void addUser(LdapUser ldapUser) {
        ldapTemplate.bind("cn=" + ldapUser.getUsername() + "," + BASE_DN, null, ldapUser.toAttributes());
    }

    public List<LdapUser> getAllUsers() {
        return ldapTemplate.search(BASE_DN, "(objectClass=inetOrgPerson)",
                (AttributesMapper<LdapUser>) attributes -> {
                    LdapUser ldapUser = new LdapUser();
                    ldapUser.setCn(attributes.get("cn").get().toString());
                    ldapUser.setSn(attributes.get("sn").get().toString());
                    ldapUser.setUsername(attributes.get("uid").get().toString());
                    ldapUser.setPassword(attributes.get("userPassword").get().toString());
                    return ldapUser;
                });
    }
}
