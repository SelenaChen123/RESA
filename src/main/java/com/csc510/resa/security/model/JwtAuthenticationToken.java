package com.csc510.resa.security.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * JWT Authentication Token Extends Spring default
 * UsernamePasswordAuthenticationToken
 *
 * @author Steven Green
 */
public class JwtAuthenticationToken implements Authentication {

    /**
     * Serializition ID
     */
    private static final long serialVersionUID = -7537447974179018679L;
    private String token;

    /**
     * Initializes with JWT token string
     *
     * @param token The JWT token
     */
    public JwtAuthenticationToken(String token) {
        // Calls super constructor with null username and password
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
