package com.sg.sleekflow.assignment.todolist.repository;

import com.sg.sleekflow.assignment.todolist.model.TodoList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TodoListRepository  extends MongoRepository<TodoList, String>, TodoListRepositoryCustom {

    List<TodoList> getTodoListsByGroupId(String groupId);
}
