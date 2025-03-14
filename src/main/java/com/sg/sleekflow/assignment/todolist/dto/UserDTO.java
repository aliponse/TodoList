package com.sg.sleekflow.assignment.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Data Transfer Object for users.
 */
@Data
public class UserDTO {

    @NotBlank(message = "User name is required")
    private String username;

    @NotBlank(message = "User password is required")
    private String password;
}
