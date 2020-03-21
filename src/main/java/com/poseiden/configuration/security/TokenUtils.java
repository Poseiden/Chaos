package com.poseiden.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Component
public class TokenUtils {
    private final static String secret = "YOUR_SECRET";

    public String getUsernameFromToken(String authToken) {
        Claims claimsFromToken = this.getClaimsFromToken(authToken);
        return isNull(claimsFromToken) ? null : claimsFromToken.getSubject();
    }

    public boolean validateToken(String authToken, UserDetails userDetails) {
        UserAuthorization user = (UserAuthorization) userDetails;
        final String username = this.getUsernameFromToken(authToken);

        return username.equals(user.getUsername()) && user.isAccountNonLocked();
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String generateToken(UserDetails userDetails) {
        UserAuthorization user = (UserAuthorization) userDetails;

        Map<String, Object> claims = generateClaimsFromUser(user);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, TokenUtils.secret)
                .compact();
    }

    private Map<String, Object> generateClaimsFromUser(UserAuthorization user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, user.getUsername());
        claims.put("role", user.getRole().value());
        return claims;
    }



}
