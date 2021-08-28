package net.zerotodev.api.security.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Log
@Component
@RequiredArgsConstructor
public class SecurityProvider implements AuthenticationProvider {
    private final UserDetailsServiceImpl userDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
