package com.sg.sleekflow.assignment.todolist.mapper;

import com.sg.sleekflow.assignment.todolist.dto.RoleDTO;
import com.sg.sleekflow.assignment.todolist.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDTO(Role role);

    Role toModel(RoleDTO roleDTO);
}