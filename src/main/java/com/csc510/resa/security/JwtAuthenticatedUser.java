package com.csc510.resa.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

/**
 * Stores details about the rest obtained from the JWT token
 *
 * @author Steven Green
 */
public class JwtAuthenticatedUser implements Authentication {

    private static final long serialVersionUID = -2193898607978371491L;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructs the rest details
     *
     * @param username           The user's username
     * @param grantedAuthorities List of authorities
     */
    public JwtAuthenticatedUser(String username, List<GrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.authorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
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
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return username;
    }
}
