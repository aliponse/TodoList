package com.sg.sleekflow.assignment.todolist.controller;

import com.sg.sleekflow.assignment.todolist.dto.TodoDTO;
import com.sg.sleekflow.assignment.todolist.mapper.TodoMapper;
import com.sg.sleekflow.assignment.todolist.model.Todo;
import com.sg.sleekflow.assignment.todolist.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todo API", description = "APIs for todo")
public class TodoController {

    private final TodoService todoService;

    // Constructor injection
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "Get todos by listId", description = "Retrieve a list of all TODOs from a certain list")
    @ApiResponse(responseCode = "200", description = "TodoList found")
    @ApiResponse(responseCode = "404", description = "TodoList not found")
    @GetMapping("/todolist/{listid}/todos")
    public List<Todo> getAllTodos(@Parameter(description = "ID of the TODO LIST") @PathVariable String listid) {
        return todoService.getAllTodos(listid);
    }

    @Operation(summary = "Get a todo by ID", description = "Retrieve a TODO by its ID")
    @ApiResponse(responseCode = "200", description = "TODO found")
    @ApiResponse(responseCode = "404", description = "TODO not found")
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@Parameter(description = "ID of the TODO") @PathVariable String id) {
        Optional<Todo> todo = todoService.getTodoById(id);
        return todo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new todo", description = "Create a new TODO")
    @ApiResponse(responseCode = "200", description = "TODO created successfully")
    @PostMapping
    public Todo addTodo(@Valid @RequestBody TodoDTO todoDTO) {

        Todo todo = TodoMapper.INSTANCE.toModel(todoDTO);
        return todoService.addTodo(todo);
    }

    @Operation(summary = "Update a todo", description = "Update an existing TODO by its ID")
    @ApiResponse(responseCode = "200", description = "TODO updated successfully")
    @ApiResponse(responseCode = "404", description = "TODO not found")
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@Parameter(description = "ID of the TODO") @PathVariable String id, @Valid @RequestBody TodoDTO todoDTO) {

        Todo updatedTodo = TodoMapper.INSTANCE.toModel(todoDTO);

        Optional<Todo> todo = todoService.updateTodo(id, updatedTodo);
        return todo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a todo", description = "Delete a TODO by its ID")
    @ApiResponse(responseCode = "204", description = "TODO deleted successfully")
    @ApiResponse(responseCode = "404", description = "TODO not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@Parameter(description = "ID of the TODO") @PathVariable String id) {
        boolean deleted = todoService.deleteTodo(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
