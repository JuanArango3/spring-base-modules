package me.jmarango.security.filters;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.jmarango.base.model.AbstractUser;
import me.jmarango.security.utils.JwtUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

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

        String token = getAccessToken(request);

        Claims claims = jwtUtils.validateAccessToken(token);
        if (claims == null) {
            filterChain.doFilter(request, response);
            return;
        }

        AbstractUser abstractUser = new AbstractUser(jwtUtils.getIdFromClaims(claims), claims.getSubject(), "") {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return jwtUtils.getAuthoritiesFromClaims(claims);
            }
        };

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(abstractUser, null, abstractUser.getAuthorities());

        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return !ObjectUtils.isEmpty(header) && header.startsWith("Bearer");
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
