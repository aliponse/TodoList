package com.sg.sleekflow.assignment.todolist.mapper;

import com.sg.sleekflow.assignment.todolist.dto.TodoDTO;
import com.sg.sleekflow.assignment.todolist.dto.TodoListDTO;
import com.sg.sleekflow.assignment.todolist.model.TodoList;
import org.mapstruct.factory.Mappers;

public interface TodoListMapper {
    TodoListMapper INSTANCE = Mappers.getMapper(TodoListMapper.class);

    TodoListDTO toDTO(TodoList todoList);

    TodoList toModel(TodoDTO todoListDTO);
}
