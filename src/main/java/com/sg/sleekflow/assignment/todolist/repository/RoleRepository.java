package com.sg.sleekflow.assignment.todolist.repository;

import com.sg.sleekflow.assignment.todolist.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for managing roles in MongoDB.
 */
@Repository
public interface RoleRepository extends MongoRepository<Role, String>, RoleRepositoryCustom {

    /**
     * Find a role by its name.
     *
     * @param name the name of the role
     * @return an Optional containing the role if found
     */
    Optional<Role> findByName(String name);
}
