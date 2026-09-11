package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic layer for User operations.
 *
 * @Service marks this class as a Spring-managed bean containing business logic.
 * Spring will detect it during component scanning and register it as a bean.
 *
 * Node.js equivalent:
 *   // userService.js
 *   const userRepository = require('./userRepository');
 *   module.exports = { getAll: () => userRepository.findAll(), ... };
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection — Spring automatically injects UserRepository bean
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User existing = getUserById(id);
        existing.setName(updatedUser.getName());
        existing.setEmail(updatedUser.getEmail());
        return userRepository.save(existing);
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User with id " + id + " deleted successfully";
    }
}
