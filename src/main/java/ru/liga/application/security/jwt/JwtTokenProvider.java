package ru.liga.application.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.liga.application.security.user.JwtUser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {
    private static final String CLAIM_USER_KEY = "user";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.token-expiration}")
    private int accessTokenExpiration;

    public String createToken(JwtUser jwtUser) {
        jwtUser.setPassword(null);
        return createToken(jwtUser, accessTokenExpiration);
    }

    public JwtUser getCurrentUser(String jwt) {
        Map map = (Map) Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(jwt)
                .getBody()
                .get(CLAIM_USER_KEY);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(map, JwtUser.class);
    }

    public boolean validate(String jwt) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(jwt);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", jwt);
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", jwt);
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", jwt);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", jwt);
        }
        return false;
    }

    private String createToken(JwtUser jwtUser, int time) {
        Date currentDate = new Date();
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USER_KEY, jwtUser);
        claims.put(Claims.SUBJECT, jwtUser.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(currentDate)
                .setExpiration(new Date(currentDate.getTime() + time))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
