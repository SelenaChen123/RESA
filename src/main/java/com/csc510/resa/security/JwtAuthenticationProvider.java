package com.csc510.resa.security;

import com.csc510.resa.security.model.JwtAuthenticationToken;
import com.csc510.resa.security.model.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Retrieves information about the rest from the JWT token for authentication
 *
 * @author Steven Green
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtValidator validator;

    /**
     * Authenticates a user
     *
     * @param authentication The JWT Authentication
     * @return Authenticated User
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();

        JwtUser jwtUser = validator.validate(token);

        if (jwtUser == null) {
            throw new BadCredentialsException("JWT token is incorrect");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (String role : jwtUser.getRole().split(",")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        JwtAuthenticatedUser authUser = new JwtAuthenticatedUser(jwtUser.getId(), grantedAuthorities);
        return authUser;
    }

    @Override
    public boolean supports(Class<?> classA) {
        return JwtAuthenticationToken.class.isAssignableFrom(classA);
    }
}
