package com.sg.sleekflow.assignment.todolist.repository;

import com.sg.sleekflow.assignment.todolist.model.Todo;

import java.util.List;

/**
 * Custom repository methods for Todo.
 */
public interface TodoRepositoryCustom {

    List<Todo> findByListId(String listId);
}
