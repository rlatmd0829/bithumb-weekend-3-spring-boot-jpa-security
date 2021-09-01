package com.example.api.security.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration // 이녀석은 뭘까
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // 프론트에서 넘어오는 시큐리티 설정
    //private final SecurityProvider provider; // provider는 토큰값을 제공하는 녀석, 시큐리티가 가지고있어야함

    @Bean // configuration은 Bean 어노테이션으로 씀
    PasswordEncoder encoder(){return new BCryptPasswordEncoder();} // 비밀번호 암호화

    @Bean
    AuthenticationManager manager() throws Exception {return super.authenticationManagerBean();} // 권한 관련?

    @Bean
    ModelMapper mapper(){return new ModelMapper();}

    @Override
    public void configure(WebSecurity web) throws Exception { // 아래 configure과 무슨차이지?
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "*/**") // ignoring은 보안을 걸지마라, **는 디렉토리, *은 파일
                .antMatchers("/", "/h2-console/");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // csrf 방식이 뚤려서 안쓴다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 사용하지 않겠다. 아이디, 비밀번호를 저장하지 않겠다.
        http.authorizeRequests()
                .antMatchers("/users/signup").permitAll()
                .antMatchers("/users/signin").permitAll()
                .antMatchers("/h2-console/**/**").permitAll()
                .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/login");
        //http.apply(new SecurityConfig(provider));
    }
}
