package com.sg.sleekflow.assignment.todolist.mapper;

import com.sg.sleekflow.assignment.todolist.dto.TodoDTO;
import com.sg.sleekflow.assignment.todolist.model.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TodoMapper {
    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    TodoDTO toDTO(Todo todo);

    Todo toModel(TodoDTO todoDTO);
}
