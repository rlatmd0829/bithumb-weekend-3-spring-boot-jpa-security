package com.example.api.security.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.example.api.user.domain.User;
import com.example.api.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter @RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService { // 기능 구현체

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(repository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException(username + "에 해당하는 객체가 존재하지 않습니다."));

        return UserDetailsImpl.build(user.get());
    }
}
