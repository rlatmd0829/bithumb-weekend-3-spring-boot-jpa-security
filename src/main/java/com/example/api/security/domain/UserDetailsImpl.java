package com.example.api.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.example.api.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails { // 도메인 구현체

    private final long actorId; // 전송 될때 바뀌면 안되는거면 final 붙임
    private final String username;
    private final String mail;
    private final String name;
    @JsonIgnore
    private final String password; // json값은 무시 => 토큰값으로 판단한다는뜻
    private final Collection<? extends GrantedAuthority> authorities; // 권한은 여러개를 중복으로 줘서 collection으로 생성


    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream() // role은 여러개라 stream 사용
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(user.getUserId(),
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
