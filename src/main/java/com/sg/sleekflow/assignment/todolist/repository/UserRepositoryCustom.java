package com.sg.sleekflow.assignment.todolist.repository;

import com.sg.sleekflow.assignment.todolist.model.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByUsername(String username); // Finds a user by username
}
