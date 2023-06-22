package com.sandrapeinados.pelugestion.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date dateNow = new Date();
        Date expirationToken = new Date(dateNow.getTime() + SecurityConstants.JWT_EXPIRATION_TOKEN);

        //Generar token
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expirationToken)
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.JWT_SIGNATURE)
                .compact();
        return token;
    }

    //Extrar username del token
    public String getUsernameOfJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SIGNATURE)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SIGNATURE).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("Token expired or is incorrect");
        }
    }
}
