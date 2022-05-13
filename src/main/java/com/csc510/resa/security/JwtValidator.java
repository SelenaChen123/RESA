package com.csc510.resa.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import com.csc510.resa.security.model.JwtUser;
import org.springframework.stereotype.Component;

/**
 * Validates JWT tokens
 *
 * @author Steven Green
 */
@Component
public class JwtValidator {

    private String secret = "secret";

    /**
     * Validates a given JWT token by
     *
     * @param token The JWT token
     * @return A JwtUser object parsed from the token
     */
    public JwtUser validate(String token) {
        JwtUser jwtUser = null;

        try {
            Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

            jwtUser = new JwtUser();

            jwtUser.setId(body.getSubject());
            jwtUser.setRole("" + body.get("role"));
        } catch (MalformedJwtException | IllegalArgumentException e) {
            // JWT is incorrect
            return null;
        }

        return jwtUser;
    }
}
