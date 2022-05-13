package com.csc510.resa.rest.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service used to generate / read info from jwt
 */
@Service
public class JwtService {

    private String secret = "secret";

    /**
     * Generates a JWT token based off a user's username
     *
     * @param user The user to generate the token for
     * @return The JWT token
     */
    public String generate(User user) {

        Date now = new Date();
        // 1 minute
        Date expire = new Date(System.currentTimeMillis() + 3600000);

        Claims claims = Jwts.claims().setSubject(user.getUsername()).setIssuedAt(now).setExpiration(expire);

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, secret).compact();
    }
}
