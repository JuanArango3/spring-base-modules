package me.jmarango.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import me.jmarango.security.dto.EnderUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {
    @Value("${app.jwt.expiration}")
    private Duration EXPIRE_DURATION;

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    private final long DEV_DURATION = 30 * 1000;

    public String generateToken(EnderUserDetails user) {
        return generateToken(user, false);
    }

    public String generateToken(EnderUserDetails abstractUser, boolean devToken) {
        List<String> authorities = abstractUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return Jwts.builder()
                .setSubject(abstractUser.getUsername())
                .claim("userId", abstractUser.getId())
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +  (devToken ? DEV_DURATION : EXPIRE_DURATION.toMillis()) ))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public Claims validateAccessToken(String token) throws JwtException, IllegalArgumentException {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Long getIdFromClaims(Claims claims) {
        return claims.get("userId", Long.class);
    }

    @SuppressWarnings("unchecked")
    public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
        List<String> authorities = (List<String>)claims.get("authorities", List.class);

        return authorities.stream().map(authority -> (GrantedAuthority) new SimpleGrantedAuthority(authority)).toList();
    }

    public String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        try {
            return header.split(" ")[1].trim();
        } catch (IndexOutOfBoundsException ex) {
            return "";
        }
    }
}
