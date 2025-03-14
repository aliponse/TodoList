package com.sg.sleekflow.assignment.todolist.model;

import com.sg.sleekflow.assignment.todolist.util.UUID;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a user role.
 */
@Data
@Document(collection = "roles")
public class Role {
    @Id
    private String id = UUID.randomUUID().toString(); // Auto-generated UUID

    @NotBlank(message = "Role name is required")
    private String name; // e.g., ROLE_USER, ROLE_ADMIN
}
