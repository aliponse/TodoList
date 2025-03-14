package com.sg.sleekflow.assignment.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object for users.
 */
@Data
public class TodoDTO {

    @NotBlank(message = "listId is mandatory")
    private String listId; // ID of the TODO_LIST

    @NotBlank(message = "Name is mandatory")
    private String name; // Name of the TODO

    private String description; // Description of the TODO

    private String crdt; // CRDT storage of the TODO

    @NotNull(message = "Due date is mandatory")
    private String dueDate; // Due date of the TODO

    @NotNull(message = "Status is mandatory")
    private String status; // Status of the TODO

    @NotNull(message = "Priority is mandatory")
    private String priority; // Priority of the TODO

    private List<String> tags; // Tags for the TODO
}
