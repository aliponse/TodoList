package com.sg.sleekflow.assignment.todolist.repository;

import com.sg.sleekflow.assignment.todolist.model.TodoList;

import java.util.List;

public interface TodoListRepositoryCustom {
    List<TodoList> getTodoListsByGroupId(String groupId);
}
