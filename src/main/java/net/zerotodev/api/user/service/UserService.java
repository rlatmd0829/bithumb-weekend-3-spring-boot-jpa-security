package net.zerotodev.api.user.service;

import net.zerotodev.api.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    // 있는게 무조건 확정이면 get으로 가져옴, 있을수도 있고 없을수도있으면 find씀
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    User signin(String username, String password);
    List<User> findAll();
    User getById(long id);
    void save(User user);
    long count();
    void deleteById(long id);
}
