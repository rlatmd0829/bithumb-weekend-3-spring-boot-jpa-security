package net.zerotodev.api.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.zerotodev.api.user.domain.User;
import net.zerotodev.api.user.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
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
