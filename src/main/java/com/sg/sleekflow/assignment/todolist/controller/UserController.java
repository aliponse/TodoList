package com.sg.sleekflow.assignment.todolist.controller;

import com.sg.sleekflow.assignment.todolist.dto.UserDTO;
import com.sg.sleekflow.assignment.todolist.mapper.UserMapper;
import com.sg.sleekflow.assignment.todolist.model.User;
import com.sg.sleekflow.assignment.todolist.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller
@RequestMapping("/api/users") // Base URL for all endpoints in this controller
@Tag(name = "User API", description = "API for user") // Swagger documentation
public class UserController {

    @Autowired // Injects UserService
    private UserService userService;

    @PostMapping("/register") // Endpoint for user registration
    @Operation(summary = "Register a new user", description = "Registers a new user.") // Swagger documentation
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) {
        User user = UserMapper.INSTANCE.toModel(userDTO);
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED); // Returns HTTP 201 (Created)
    }

    @GetMapping // Endpoint for retrieving all users
    @Operation(summary = "Get all users", description = "Returns a list of all users.") // Swagger documentation
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK); // Returns HTTP 200 (OK)
    }
}