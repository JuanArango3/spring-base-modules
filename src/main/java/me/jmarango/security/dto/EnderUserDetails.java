package me.jmarango.security.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;

@Data
@RequiredArgsConstructor
public class EnderUserDetails implements UserDetails {

    private final Long id;

    private final String username;

    private final String password;

    private final Instant tokenIssuedAt;

    private final Collection<? extends GrantedAuthority> authorities;
}
