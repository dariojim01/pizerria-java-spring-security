package com.pizza.pizzeria.web.config;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;

import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "p1z1zz3";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
    public String create(String username){

        return JWT.create()
                .withSubject(username)
                .withIssuer("pizzeria-dj")
                .withIssuedAt(new Date())
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .sign(algorithm);
    }

    public boolean isValid(String token) {
        try {
            JWT.require(algorithm)
                    .build()
                    .verify(token);
            return true;
        }catch (Exception e) {
            return false;
        }

    }
    public String getUsername(String token) {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
    }

}
