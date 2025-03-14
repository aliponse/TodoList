package com.sg.sleekflow.assignment.todolist.util;

public class UUID {

    public static Long randomUUID()
    {
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator(1);
        return generator.nextId();
    }
}
