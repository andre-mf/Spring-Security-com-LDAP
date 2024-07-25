package com.example.springldap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                // autenticação básica
        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().fullyAuthenticated())
                .httpBasic(Customizer.withDefaults());
                // autenticação em página de login
//        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());

        return http.build();
    }

//    @Bean
//    public LdapTemplate ldapTemplate() {
//        return new LdapTemplate(contextSource());
//    }

//    @Bean
//    public LdapContextSource contextSource() {
//        LdapContextSource ldapContextSource = new LdapContextSource();
//        ldapContextSource.setUrl("ldap://localhost:389");
//        ldapContextSource.setBase("dc=example,dc=org");
//        ldapContextSource.setUserDn("cn=admin,dc=example,dc=org");
//        ldapContextSource.setPassword("admin");
//        return ldapContextSource;
//    }

    @Bean
    AuthenticationManager authenticationManager(BaseLdapPathContextSource contextSource) {
        LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(contextSource);
        factory.setUserSearchBase("cn=users");
        factory.setUserSearchFilter("(uid={0})");
        return factory.createAuthenticationManager();
    }
}
