package com.sg.sleekflow.assignment.todolist.mapper;

import com.sg.sleekflow.assignment.todolist.dto.UserDTO;
import com.sg.sleekflow.assignment.todolist.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toModel(UserDTO userDTO);
}
