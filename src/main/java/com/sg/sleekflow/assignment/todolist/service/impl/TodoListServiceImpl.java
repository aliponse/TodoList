package com.sg.sleekflow.assignment.todolist.service.impl;

import com.sg.sleekflow.assignment.todolist.model.TodoList;
import com.sg.sleekflow.assignment.todolist.repository.TodoListRepository;
import com.sg.sleekflow.assignment.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    private TodoListRepository todoListRepository; // Inject the repository

    @Override
    public List<TodoList> getAllTodoLists() {
        return todoListRepository.findAll();
    }

    @Override
    public Optional<TodoList> getTodoListById(String id) {
        return todoListRepository.findById(id);
    }

    @Override
    public List<TodoList> getTodoListsByGroupId(String groupId) {
        return null;
    }

    @Override
    public List<TodoList> getTodoListsByUserId(String userId) {

        //TODO get from redis cache
        return null;
    }

    @Override
    public TodoList addTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    @Override
    public Optional<TodoList> updateTodoList(String id, TodoList updatedTodoList) {
        return todoListRepository.findById(id).map(todoList -> {
            todoList.setName(updatedTodoList.getName());
            todoList.setDescription(updatedTodoList.getDescription());
            todoList.setStatus(updatedTodoList.getStatus());
            todoList.setGroupId(updatedTodoList.getGroupId());
            todoList.setOwnerId(updatedTodoList.getOwnerId());
            todoList.setCollaborators(updatedTodoList.getCollaborators());
            todoList.setTodos(updatedTodoList.getTodos());
            return todoListRepository.save(todoList);
        });
    }

    @Override
    public boolean deleteTodoList(String id) {
        if (todoListRepository.existsById(id)) {
            todoListRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
