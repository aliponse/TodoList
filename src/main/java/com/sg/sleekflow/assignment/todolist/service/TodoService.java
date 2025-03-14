package com.sg.sleekflow.assignment.todolist.service;

import com.sg.sleekflow.assignment.todolist.model.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getAllTodos(String listId); // Get all TODOs by todoListId
    Optional<Todo> getTodoById(String id); // Get a TODO by ID
    Todo addTodo(Todo todo); // Add a new TODO
    Optional<Todo> updateTodo(String id, Todo updatedTodo); // Update a TODO
    boolean deleteTodo(String id); // Delete a TODO
}
