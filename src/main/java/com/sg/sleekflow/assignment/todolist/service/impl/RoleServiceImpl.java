package com.sg.sleekflow.assignment.todolist.service.impl;

import com.sg.sleekflow.assignment.todolist.model.Role;
import com.sg.sleekflow.assignment.todolist.repository.RoleRepository;
import com.sg.sleekflow.assignment.todolist.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(String id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }
}
