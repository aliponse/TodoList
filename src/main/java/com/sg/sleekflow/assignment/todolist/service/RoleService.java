package com.sg.sleekflow.assignment.todolist.service;

import com.sg.sleekflow.assignment.todolist.model.Role;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing roles.
 */
public interface RoleService {
    Role createRole(Role role);
    List<Role> getAllRoles();
    Role getRoleById(String id);
    Optional<Role> getRoleByName(String name);
    void deleteRole(String id);
}