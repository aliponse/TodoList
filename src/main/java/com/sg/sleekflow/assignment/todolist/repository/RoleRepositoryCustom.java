package com.sg.sleekflow.assignment.todolist.repository;

import com.sg.sleekflow.assignment.todolist.model.Role;

import java.util.Optional;

/**
 * Custom repository methods for Role.
 */
public interface RoleRepositoryCustom {
    Optional<Role> findByNameCustom(String name);
}
