package com.sg.sleekflow.assignment.todolist.controller;

import com.sg.sleekflow.assignment.todolist.dto.TodoListDTO;
import com.sg.sleekflow.assignment.todolist.mapper.TodoListMapper;
import com.sg.sleekflow.assignment.todolist.mapper.TodoMapper;
import com.sg.sleekflow.assignment.todolist.model.Todo;
import com.sg.sleekflow.assignment.todolist.model.TodoList;
import com.sg.sleekflow.assignment.todolist.service.TodoListService;
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
@RequestMapping("/api/todolists")
@Tag(name = "Todolist API", description = "APIs for todolist")
public class TodoListController {

    private final TodoListService todoListService;

    // Constructor injection
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    /*@Operation(summary = "Get all TodoLists", description = "Retrieve a list of all TodoLists")
    @GetMapping
    public List<TodoList> getAllTodoLists() {
        return todoListService.getAllTodoLists();
    }*/

    @Operation(summary = "Get todoLists by groupId", description = "Retrieve a list of all TodoLists by groupId")
    @ApiResponse(responseCode = "200", description = "TodoList found")
    @ApiResponse(responseCode = "404", description = "TodoList not found")
    @GetMapping("/groups/{groupid}/todolists")
    public List<TodoList> getAllTodosByGroupId(@Parameter(description = "ID of the TODO LIST") @PathVariable String groupid) {
        return todoListService.getTodoListsByGroupId(groupid);
    }

    @Operation(summary = "Get todoLists by userId", description = "Retrieve a list of all TodoLists by userId")
    @ApiResponse(responseCode = "200", description = "TodoList found")
    @ApiResponse(responseCode = "404", description = "TodoList not found")
    @GetMapping("/users/{userid}/todolists")
    public List<TodoList> getAllTodosByUserId(@Parameter(description = "ID of the TODO LIST") @PathVariable String userid) {
        return todoListService.getTodoListsByUserId(userid);
    }

    @Operation(summary = "Get a todoList by ID", description = "Retrieve a TodoList by its ID")
    @ApiResponse(responseCode = "200", description = "TodoList found")
    @ApiResponse(responseCode = "404", description = "TodoList not found")
    @GetMapping("/{id}")
    public ResponseEntity<TodoList> getTodoListById(@Parameter(description = "ID of the TodoList") @PathVariable String id) {
        Optional<TodoList> todoList = todoListService.getTodoListById(id);
        return todoList.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a new todoList", description = "Create a new TodoList")
    @ApiResponse(responseCode = "200", description = "TodoList created successfully")
    @PostMapping
    public TodoList addTodoList(@Valid @RequestBody TodoListDTO todoListDTO) {

        TodoList todoList = TodoListMapper.INSTANCE.toModel(todoListDTO);
        return todoListService.addTodoList(todoList);
    }

    @Operation(summary = "Update a todoList", description = "Update an existing TodoList by its ID")
    @ApiResponse(responseCode = "200", description = "TodoList updated successfully")
    @ApiResponse(responseCode = "404", description = "TodoList not found")
    @PutMapping("/{id}")
    public ResponseEntity<TodoList> updateTodoList(@Parameter(description = "ID of the TodoList") @PathVariable String id, @Valid @RequestBody TodoListDTO todoListDTO) {

        TodoList updatedTodoList = TodoListMapper.INSTANCE.toModel(todoListDTO);
        Optional<TodoList> todoList = todoListService.updateTodoList(id, updatedTodoList);
        return todoList.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a todoList", description = "Delete a TodoList by its ID")
    @ApiResponse(responseCode = "204", description = "TodoList deleted successfully")
    @ApiResponse(responseCode = "404", description = "TodoList not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoList(@Parameter(description = "ID of the TodoList") @PathVariable String id) {
        boolean deleted = todoListService.deleteTodoList(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
