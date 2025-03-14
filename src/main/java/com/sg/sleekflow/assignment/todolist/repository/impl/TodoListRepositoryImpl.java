package com.sg.sleekflow.assignment.todolist.repository.impl;

import com.sg.sleekflow.assignment.todolist.model.TodoList;
import com.sg.sleekflow.assignment.todolist.repository.TodoListRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoListRepositoryImpl implements TodoListRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TodoListRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<TodoList> getTodoListsByGroupId(String groupId) {
        Query query = new Query(Criteria.where("groupId").is(groupId));
        List<TodoList> lists = mongoTemplate.find(query, TodoList.class);
        return lists;
    }
}
