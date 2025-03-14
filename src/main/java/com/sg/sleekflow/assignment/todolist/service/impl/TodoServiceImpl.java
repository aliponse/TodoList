package com.sg.sleekflow.assignment.todolist.service.impl;

import com.sg.sleekflow.assignment.todolist.model.Todo;
import com.sg.sleekflow.assignment.todolist.repository.TodoRepository;
import com.sg.sleekflow.assignment.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository; // Inject the repository

    @Override
    public List<Todo> getAllTodos(String listId) {
        return todoRepository.findByListId(listId);
    }

    @Override
    public Optional<Todo> getTodoById(String id) {
        return todoRepository.findById(id);
    }

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Optional<Todo> updateTodo(String id, Todo updatedTodo) {
        return todoRepository.findById(id).map(todo -> {
            todo.setName(updatedTodo.getName());
            todo.setDescription(updatedTodo.getDescription());
            todo.setCrdt(updatedTodo.getCrdt());
            todo.setDueDate(updatedTodo.getDueDate());
            todo.setStatus(updatedTodo.getStatus());
            todo.setPriority(updatedTodo.getPriority());
            todo.setTags(updatedTodo.getTags());
            return todoRepository.save(todo);
        });
    }

    @Override
    public boolean deleteTodo(String id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
