package kz.edu.astanait.gatewayservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.List;

/**
 * @author aldi
 * @since 24.03.2024
 */

@Slf4j
@Component
public class JwtTokenUtils {
    @Value("${jwt.secret}")
    private String secret;

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Token {} is not valid", token);
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return getAllClaims(token).get("email", String.class);
    }

    public List<String> getRole(String token) {
        return getAllClaims(token).get("role", List.class);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
