package com.sg.sleekflow.assignment.todolist.service;

import com.sg.sleekflow.assignment.todolist.model.Todo;
import com.sg.sleekflow.assignment.todolist.model.TodoList;

import java.util.List;
import java.util.Optional;

public interface TodoListService {
    List<TodoList> getAllTodoLists(); // Get all TodoLists
    Optional<TodoList> getTodoListById(String id); // Get a TodoList by ID
    List<TodoList> getTodoListsByGroupId(String groupId); // Get all TodoLists by GroupId
    List<TodoList> getTodoListsByUserId(String userId); // Get all TodoLists by UserId
    TodoList addTodoList(TodoList todoList); // Add a new TodoList
    Optional<TodoList> updateTodoList(String id, TodoList updatedTodoList); // Update a TodoList
    boolean deleteTodoList(String id); // Delete a TodoList
}
