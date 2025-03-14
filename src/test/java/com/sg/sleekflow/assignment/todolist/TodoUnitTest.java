package com.sg.sleekflow.assignment.todolist;

import com.sg.sleekflow.assignment.todolist.dto.TodoDTO;
import com.sg.sleekflow.assignment.todolist.mapper.TodoMapper;
import com.sg.sleekflow.assignment.todolist.model.Todo;
import com.sg.sleekflow.assignment.todolist.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class TodoUnitTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testGetAllTodos() throws Exception {
        Todo todo = new Todo();
        todo.setId("1");
        todo.setListId("list1");
        todo.setName("Task 1");
        todo.setDescription("desp1");
        todo.setCrdt("CRDT Content 1");
        todo.setDueDate("2025-5-5 00:00:00");
        todo.setStatus("1");
        todo.setPriority("2");
        todo.setTags(Arrays.asList("work","red"));

        Todo todo2 = new Todo();
        todo2.setId("2");
        todo2.setListId("list1");
        todo2.setName("Task 2");
        todo2.setDescription("desp2");
        todo2.setCrdt("CRDT Content 2");
        todo2.setDueDate("2025-5-4 00:00:00");
        todo2.setStatus("1");
        todo2.setPriority("2");
        todo2.setTags(Arrays.asList("work","blue"));

        // Mock data
        List<Todo> todos = Arrays.asList(todo,todo2);

        // Mock service
        when(todoService.getAllTodos("list1")).thenReturn(todos);

        // Perform GET request and verify
        mockMvc.perform(get("/api/todos/todolist/list1/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Task 1"))
                .andExpect(jsonPath("$[1].name").value("Task 2"));
    }

    @Test
    void testGetTodoById() throws Exception {
        // Mock data
        Todo todo = new Todo();
        todo.setId("1");
        todo.setListId("list1");
        todo.setName("Task 1");
        todo.setDescription("desp1");
        todo.setCrdt("CRDT Content 1");
        todo.setDueDate("2025-5-5 00:00:00");
        todo.setStatus("1");
        todo.setPriority("2");
        todo.setTags(Arrays.asList("work","red"));

        // Mock service
        when(todoService.getTodoById("1")).thenReturn(Optional.of(todo));

        // Perform GET request and verify
        mockMvc.perform(get("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Task 1"));
    }

    @Test
    void testGetTodoById_NotFound() throws Exception {
        // Mock service
        when(todoService.getTodoById("1")).thenReturn(Optional.empty());

        // Perform GET request and verify
        mockMvc.perform(get("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddTodo() throws Exception {
        // Mock data
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setListId("list1");
        todoDTO.setName("Task 1");
        todoDTO.setDescription("desp1");
        todoDTO.setCrdt("CRDT Content 1");
        todoDTO.setDueDate("2025-5-5 00:00:00");
        todoDTO.setStatus("1");
        todoDTO.setPriority("2");
        todoDTO.setTags(Arrays.asList("work","red"));

        Todo savedTodo = new Todo();
        savedTodo.setId("1");
        savedTodo.setListId("list1");
        savedTodo.setName("Task 1");
        savedTodo.setDescription("desp1");
        savedTodo.setCrdt("CRDT Content 1");
        savedTodo.setDueDate("2025-5-5 00:00:00");
        savedTodo.setStatus("1");
        savedTodo.setPriority("2");
        savedTodo.setTags(Arrays.asList("work","red"));

        // Mock service
        when(todoService.addTodo(any(Todo.class))).thenReturn(savedTodo);

        // Perform POST request and verify
        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void testUpdateTodo() throws Exception {
        // Mock data
        Todo todo = new Todo();
        todo.setId("1");
        todo.setListId("list1");
        todo.setName("Task 12");
        todo.setDescription("desp12");
        todo.setCrdt("CRDT Content 12");
        todo.setDueDate("2025-5-5 00:00:00");
        todo.setStatus("3");
        todo.setPriority("4");
        todo.setTags(Arrays.asList("a","red"));

        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setListId("list1");
        todoDTO.setName("Task 12");
        todoDTO.setDescription("desp12");
        todoDTO.setCrdt("CRDT Content 12");
        todoDTO.setDueDate("2025-5-5 00:00:00");
        todoDTO.setStatus("3");
        todoDTO.setPriority("4");
        todoDTO.setTags(Arrays.asList("a","red"));

        // Mock service
        when(todoService.updateTodo("1", todo)).thenReturn(Optional.of(todo));

        // Perform PUT request and verify
        mockMvc.perform(put("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Task 12"));
    }

    @Test
    void testUpdateTodo_NotFound() throws Exception {
        // Mock data
        Todo todo = new Todo();
        todo.setId("1");
        todo.setListId("list1");
        todo.setName("Task 12");
        todo.setDescription("desp12");
        todo.setCrdt("CRDT Content 12");
        todo.setDueDate("2025-5-5 00:00:00");
        todo.setStatus("3");
        todo.setPriority("4");
        todo.setTags(Arrays.asList("a","red"));

        // Mock service
        when(todoService.updateTodo("1", todo)).thenReturn(Optional.empty());

        // Perform PUT request and verify
        mockMvc.perform(put("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todo)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteTodo() throws Exception {
        // Mock service
        when(todoService.deleteTodo("1")).thenReturn(true);

        // Perform DELETE request and verify
        mockMvc.perform(delete("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteTodo_NotFound() throws Exception {
        // Mock service
        when(todoService.deleteTodo("1")).thenReturn(false);

        // Perform DELETE request and verify
        mockMvc.perform(delete("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
