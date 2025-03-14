package com.sg.sleekflow.assignment.todolist.service;

import com.sg.sleekflow.assignment.todolist.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user); // Registers a new user
    User findByUsername(String username); // Finds a user by username
    List<User> getAllUsers(); // Retrieves all users
}
