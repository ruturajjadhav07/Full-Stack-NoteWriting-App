package com.relation.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.relation.demo.entity.User;
import com.relation.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // create user
    public User createUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (user.getUsername().equals(user.getPassword())) {
            throw new IllegalArgumentException("username and password cannot be same");
        }

        return userRepository.save(user);
    }

    // edit user details
    public User editUserById(int id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id:" + id));

        if (updatedUser.getUsername() == null || updatedUser.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (updatedUser.getPassword() == null || updatedUser.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (updatedUser.getUsername().equals(updatedUser.getPassword())) {
            throw new IllegalArgumentException("Username and password cannot be the same");
        }

        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());

        return userRepository.save(user);
    }

    // delete user by id
    public String deleteUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        userRepository.delete(user);
        return "User with ID " + id + " deleted successfully";
    }

}
