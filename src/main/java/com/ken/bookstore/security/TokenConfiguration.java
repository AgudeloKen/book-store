package com.ken.bookstore.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ken.bookstore.models.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenConfiguration {

    private final String secret = "12345";

    public DecodedJWT getSubject(String token){
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            decodedJWT = JWT
                    .require(algorithm)
                    .withIssuer("Ken")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            return null;
        }
        return decodedJWT;
    }

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Ken")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(expirationTime())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            return null;
        }
    }

    public Instant expirationTime(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

}
