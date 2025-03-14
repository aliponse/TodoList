package com.sg.sleekflow.assignment.todolist.mapper;

import com.sg.sleekflow.assignment.todolist.dto.CollaboratorDTO;
import com.sg.sleekflow.assignment.todolist.dto.TodoListDTO;
import com.sg.sleekflow.assignment.todolist.model.Collaborator;
import com.sg.sleekflow.assignment.todolist.model.TodoList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TodoListMapper {

    TodoListMapper INSTANCE = Mappers.getMapper(TodoListMapper.class);

    TodoListDTO toDTO(TodoList todoList);

    TodoList toModel(TodoListDTO todoListDTO);

}
