package com.example.api.security.config;

import com.example.api.security.domain.SecurityProvider;
import com.example.api.security.filter.SecurityFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor // 생성자 주입
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> { // 내부적 db에 저장할때 쓰는 시큐리티
    private final SecurityProvider provider;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SecurityFilter filter = new SecurityFilter(provider); // filter 폴더에 있는 securityFilter로 filter 만듬
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class); // 이미 만들어진 UsernamePassfilter 가져옴
    }
}
