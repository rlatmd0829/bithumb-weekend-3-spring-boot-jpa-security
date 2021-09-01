package com.example.api.user.service;

import com.example.api.security.domain.SecurityProvider;
import com.example.api.security.exception.SecurityRuntimeException;
import com.example.api.user.domain.Role;
import com.example.api.user.domain.User;
import com.example.api.user.domain.UserDto;
import com.example.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final SecurityProvider provider;
    private final ModelMapper modelMapper;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDto signin(User user) { // 로그인
        userRepository.signin(user.getUsername(), user.getPassword());
        try{
            UserDto userDto = modelMapper.map(user, UserDto.class);
            String token = (encoder.matches(user.getPassword(),
                    userRepository.findByUsername(user.getUsername()).get().getPassword())) // Optional은 get으로 꺼내준다.
                    ? provider.createToken(user.getUsername(), userRepository.findByUsername(user.getUsername()).get().getRoles())// 참
                    : "Wrong Password";// 거짓;
            userDto.setToken(token);
            return userDto;
        }catch (Exception e){
            throw new SecurityRuntimeException("유효하지 않는 아이디 / 비밀번호", HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @Override
    public String signup(User user) {
        if(!userRepository.existsByUsername(user.getUsername())){
            user.setPassword(encoder.encode(user.getPassword()));
            List<Role> list = new ArrayList<>();
            list.add(Role.USER);
            user.setRoles(list);
            userRepository.save(user);
            return provider.createToken(user.getUsername(), user.getRoles());

        }else{
            throw new SecurityRuntimeException("중복된 ID 입니다.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
