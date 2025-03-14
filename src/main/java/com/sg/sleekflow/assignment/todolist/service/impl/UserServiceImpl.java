package com.sg.sleekflow.assignment.todolist.service.impl;

import com.sg.sleekflow.assignment.todolist.model.User;
import com.sg.sleekflow.assignment.todolist.repository.UserRepository;
import com.sg.sleekflow.assignment.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as a Spring service component
@RequiredArgsConstructor // Lombok annotation to generate a constructor with required arguments
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository; // Injects UserRepository
    private final PasswordEncoder passwordEncoder; // Injects PasswordEncoder

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encodes the password
        return userRepository.save(user); // Saves the user to the database
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null); // Finds a user by username
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Retrieves all users from the database
    }
}
