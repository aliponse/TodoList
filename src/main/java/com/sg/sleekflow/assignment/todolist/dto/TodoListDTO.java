package com.sg.sleekflow.assignment.todolist.dto;

import com.sg.sleekflow.assignment.todolist.model.Collaborator;
import com.sg.sleekflow.assignment.todolist.model.Todo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object for todoLists.
 */
@Data
public class TodoListDTO {

    @NotBlank(message = "Name is mandatory")
    private String groupId; // group of the TODO List

    @NotBlank(message = "Name is mandatory")
    private String ownerId; // owner of the TODO List

    @NotBlank(message = "Name is mandatory")
    private String name; // Name of the TODO List

    private String description; // Description of the TODO List

    @NotNull(message = "Status is mandatory")
    private String status; // Status open or close

    List<String> todos; // todos in TodoList

    List<Collaborator> collaborators; // Users in TodoList
}
