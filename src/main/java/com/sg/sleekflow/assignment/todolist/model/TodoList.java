package com.sg.sleekflow.assignment.todolist.model;

import com.sg.sleekflow.assignment.todolist.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "TodoLists")
public class TodoList {

    @Id
    private String id = UUID.randomUUID().toString(); // Unique identifier for the TODO List

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
