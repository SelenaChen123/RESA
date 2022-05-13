package com.csc510.resa.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handler for successful authentication
 *
 * @author Steven Green
 */
public class JwtSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Called on successful authentication
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        // SpringBootApp.getLogger().info("Successfully authenticated");
    }
}
