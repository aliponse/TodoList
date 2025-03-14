package com.sg.sleekflow.assignment.todolist.repository;

import com.sg.sleekflow.assignment.todolist.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String>, TodoRepositoryCustom {

    /**
     * Find a Todo by Todo List ID.
     *
     * @param listId the id of the todolist
     * @return an list containing the todo if found
     */
    List<Todo> findByListId(String listId);
}
