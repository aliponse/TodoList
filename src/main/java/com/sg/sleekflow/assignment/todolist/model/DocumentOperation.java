package com.sg.sleekflow.assignment.todolist.model;

import lombok.Data;

@Data
public class DocumentOperation {

    // Operation Type : 'INSERT_CHAR' | 'DELETE_CHAR';
    private char type;

    // Insert characters
    private String value;

    // Operation position
    private int position;

    // Operation time
    private String timestamp;
}
