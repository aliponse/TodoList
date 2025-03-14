package com.sg.sleekflow.assignment.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * Data Transfer Object for roles.
 */
@Data
public class RoleDTO {

    @NotBlank(message = "Role name is required")
    private String name;
}
