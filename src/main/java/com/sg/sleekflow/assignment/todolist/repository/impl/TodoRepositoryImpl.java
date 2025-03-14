package com.sg.sleekflow.assignment.todolist.repository.impl;

import com.sg.sleekflow.assignment.todolist.model.Todo;
import com.sg.sleekflow.assignment.todolist.repository.TodoRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of custom repository methods for Todo.
 */
@Repository
public class TodoRepositoryImpl implements TodoRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TodoRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Todo> findByListId(String listId) {
        Query query = new Query(Criteria.where("listId").is(listId));
        List<Todo> lists = mongoTemplate.find(query, Todo.class);
        return lists;
    }
}
