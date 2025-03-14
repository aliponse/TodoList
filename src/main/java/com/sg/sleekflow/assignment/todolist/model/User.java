package com.sg.sleekflow.assignment.todolist.model;

import com.sg.sleekflow.assignment.todolist.util.UUID;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id = UUID.randomUUID().toString(); // Auto-generated UUID
    @NotBlank(message = "User name is required")
    private String username;
    @NotBlank(message = "User password is required")
    private String password;
    private Set<Role> roles; // User Role
}