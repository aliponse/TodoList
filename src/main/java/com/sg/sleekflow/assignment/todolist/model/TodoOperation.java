package com.sg.sleekflow.assignment.todolist.model;

import lombok.Data;

import java.util.List;

@Data
public class TodoOperation {

    // Operation Type : 'ADD_TASK' | 'UPDATE_TASK' | 'DELETE_TASK';
    private char type;

    // Todolist ID
    private String listId;

    // Todo ID
    private String todoId;

    // Operation exclude Document
    private Todo todoOpts;

    // DocumentOperation list
    List<DocumentOperation> docOpts;

    // Operation time
    private String timestamp;
}
