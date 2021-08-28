package net.zerotodev.api.user.service;

import lombok.RequiredArgsConstructor;
import net.zerotodev.api.user.domain.User;
import net.zerotodev.api.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User signin(String username, String password) {
        return userRepository.signin(username, password);
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
