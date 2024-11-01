package com.backendkiss.backendkiss.resolver;

import org.springframework.stereotype.Component;

import com.backendkiss.backendkiss.entity.User;
import com.backendkiss.backendkiss.repository.UserRepository;
import java.util.List;

@Component
public class UserQueryResolver {

    private final UserRepository userRepository;

    public UserQueryResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        return userRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
