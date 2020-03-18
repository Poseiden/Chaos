package com.poseiden.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenUtils {
    public String getUsernameFromToken(String authToken) {
        Claims claimsFromToken = this.getClaimsFromToken(authToken);
        return claimsFromToken.getSubject();
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
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

}
