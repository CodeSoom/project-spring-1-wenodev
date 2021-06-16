package com.weno.security;

import com.weno.role.Role;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class UserAuthentication extends AbstractAuthenticationToken {

    private final String email;

    public UserAuthentication(List<Role> roles, String email) {
        super(authorities(roles));
        this.email = email;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    private static List<GrantedAuthority> authorities(List<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());
    }
}
