package com.sg.sleekflow.assignment.todolist.controller;

import com.sg.sleekflow.assignment.todolist.dto.RoleDTO;
import com.sg.sleekflow.assignment.todolist.mapper.RoleMapper;
import com.sg.sleekflow.assignment.todolist.model.Role;
import com.sg.sleekflow.assignment.todolist.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing roles.
 */
@RestController
@RequestMapping("/api/roles")
@Tag(name = "Role API", description = "API for role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    @Operation(summary = "Create a new role", description = "Creates a new role.")
    public ResponseEntity<Role> createRole(@Valid @RequestBody RoleDTO roleDTO) {
        Role role = RoleMapper.INSTANCE.toModel(roleDTO);
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all roles", description = "Returns a list of all roles.")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get role by ID", description = "Returns a role by its ID.")
    public ResponseEntity<Role> getRoleById(@PathVariable String id) {
        return new ResponseEntity<>(roleService.getRoleById(id), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get role by name", description = "Returns a role by its name.")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        Optional<Role> role = roleService.getRoleByName(name);
        return role.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a role", description = "Deletes a role by its ID.")
    public ResponseEntity<Void> deleteRole(@PathVariable String id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
