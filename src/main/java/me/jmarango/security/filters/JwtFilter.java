package me.jmarango.security.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.jmarango.security.dto.EnderUserDetails;
import me.jmarango.security.utils.JwtUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (!hasAuthorizationBearer(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwtUtils.getAccessToken(request);

        Claims claims;
        try {
            claims = jwtUtils.validateAccessToken(token);
        } catch (JwtException | IllegalArgumentException e) {
            filterChain.doFilter(request, response);
            return;
        }

        EnderUserDetails abstractUser = new EnderUserDetails(
                jwtUtils.getIdFromClaims(claims),
                claims.getSubject(),
                null,
                claims.getIssuedAt().toInstant(),
                jwtUtils.getAuthoritiesFromClaims(claims));

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(abstractUser, null, abstractUser.getAuthorities());

        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return !ObjectUtils.isEmpty(header) && header.startsWith("Bearer");
    }
}
