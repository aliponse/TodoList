package com.sg.sleekflow.assignment.todolist;

import com.sg.sleekflow.assignment.todolist.model.Collaborator;
import com.sg.sleekflow.assignment.todolist.model.Todo;
import com.sg.sleekflow.assignment.todolist.model.TodoList;
import com.sg.sleekflow.assignment.todolist.service.TodoListService;
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
class TodoListUnitTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private TodoListService todoListService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testGetTodoListById() throws Exception {

        // Mock data
        TodoList todoList = new TodoList();
        todoList.setId("1");
        todoList.setName("worktasks");
        todoList.setDescription("desp1");
        todoList.setStatus("1");
        todoList.setOwnerId("user1");
        todoList.setGroupId("group1");
        todoList.setTodos(Arrays.asList("todo1","todo2"));
        Collaborator collaborator = new Collaborator();
        collaborator.setUserId("user1");
        collaborator.setPerm("1");
        todoList.setCollaborators(Arrays.asList(collaborator));

        // Mock service
        when(todoListService.getTodoListById("1")).thenReturn(Optional.of(todoList));

        // Perform GET request and verify
        mockMvc.perform(get("/api/todolist/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("worktasks"));
    }

    @Test
    void testGetTodoListById_NotFound() throws Exception {
        // Mock service
        when(todoListService.getTodoListById("1")).thenReturn(Optional.empty());

        // Perform GET request and verify
        mockMvc.perform(get("/api/todolist/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddTodoList() throws Exception {
        // Mock data
        TodoList todoList = new TodoList();
        todoList.setId("1");
        todoList.setName("worktasks");
        todoList.setDescription("desp1");
        todoList.setStatus("1");
        todoList.setOwnerId("user1");
        todoList.setGroupId("group1");
        todoList.setTodos(Arrays.asList("todo1","todo2"));
        Collaborator collaborator = new Collaborator();
        collaborator.setUserId("user1");
        collaborator.setPerm("1");
        todoList.setCollaborators(Arrays.asList(collaborator));

        TodoList savedTodoList = new TodoList();
        savedTodoList.setId("1");
        savedTodoList.setName("worktasks");
        savedTodoList.setDescription("desp1");
        savedTodoList.setStatus("1");
        savedTodoList.setOwnerId("user1");
        savedTodoList.setGroupId("group1");
        savedTodoList.setTodos(Arrays.asList("todo1","todo2"));
        savedTodoList.setCollaborators(Arrays.asList(collaborator));

        // Mock service
        when(todoListService.addTodoList(any(TodoList.class))).thenReturn(savedTodoList);

        // Perform POST request and verify
        mockMvc.perform(post("/api/todolist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void testUpdateTodoList() throws Exception {
        // Mock data
        TodoList todoList = new TodoList();
        todoList.setId("1");
        todoList.setName("worktasks");
        todoList.setDescription("desp1");
        todoList.setStatus("1");
        todoList.setOwnerId("user1");
        todoList.setGroupId("group1");
        todoList.setTodos(Arrays.asList("todo1","todo2"));
        Collaborator collaborator = new Collaborator();
        collaborator.setUserId("user1");
        collaborator.setPerm("1");
        todoList.setCollaborators(Arrays.asList(collaborator));

        // Mock service
        when(todoListService.updateTodoList("1", todoList)).thenReturn(Optional.of(todoList));

        // Perform PUT request and verify
        mockMvc.perform(put("/api/todolist/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("worktasks"));
    }

    @Test
    void testUpdateTodoList_NotFound() throws Exception {
        // Mock data
        TodoList todoList = new TodoList();
        todoList.setId("1");
        todoList.setName("worktasks");
        todoList.setDescription("desp1");
        todoList.setStatus("1");
        todoList.setOwnerId("user1");
        todoList.setGroupId("group1");
        todoList.setTodos(Arrays.asList("todo1","todo2"));
        Collaborator collaborator = new Collaborator();
        collaborator.setUserId("user1");
        collaborator.setPerm("1");
        todoList.setCollaborators(Arrays.asList(collaborator));

        // Mock service
        when(todoListService.updateTodoList("2", todoList)).thenReturn(Optional.empty());

        // Perform PUT request and verify
        mockMvc.perform(put("/api/todolist/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoList)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteTodoList() throws Exception {
        // Mock service
        when(todoListService.deleteTodoList("1")).thenReturn(true);

        // Perform DELETE request and verify
        mockMvc.perform(delete("/api/todolist/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteTodoList_NotFound() throws Exception {
        // Mock service
        when(todoListService.deleteTodoList("1")).thenReturn(false);

        // Perform DELETE request and verify
        mockMvc.perform(delete("/api/todolist/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}