package com.sg.sleekflow.assignment.todolist.repository.impl;

import com.sg.sleekflow.assignment.todolist.model.Role;
import com.sg.sleekflow.assignment.todolist.repository.RoleRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Implementation of custom repository methods for Role.
 */
@Repository
public class RoleRepositoryImpl implements RoleRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public RoleRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<Role> findByNameCustom(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        Role role = mongoTemplate.findOne(query, Role.class);
        return Optional.ofNullable(role);
    }
}