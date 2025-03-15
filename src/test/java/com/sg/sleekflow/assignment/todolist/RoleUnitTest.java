package com.sg.sleekflow.assignment.todolist;

import com.sg.sleekflow.assignment.todolist.controller.RoleController;
import com.sg.sleekflow.assignment.todolist.model.Role;
import com.sg.sleekflow.assignment.todolist.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RoleUnitTest {

    private MockMvc mockMvc;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }

    @Test
    void createRole() throws Exception {
        Role role = new Role();
        role.setId("1");
        role.setName("ADMIN");

        when(roleService.createRole(any(Role.class))).thenReturn(role);

        mockMvc.perform(post("/api/roles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"ADMIN\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("ADMIN"));

        verify(roleService, times(1)).createRole(any(Role.class));
    }

    @Test
    void getRoleById() throws Exception {
        Role role = new Role();
        role.setId("1");
        role.setName("ADMIN");

        when(roleService.getRoleById("1")).thenReturn(role);

        mockMvc.perform(get("/api/roles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("ADMIN"));

        verify(roleService, times(1)).getRoleById("1");
    }

    @Test
    void getAllRoles() throws Exception {
        Role role1 = new Role();
        role1.setId("1");
        role1.setName("ADMIN");

        Role role2 = new Role();
        role2.setId("2");
        role2.setName("USER");

        when(roleService.getAllRoles()).thenReturn(Arrays.asList(role1, role2));

        mockMvc.perform(get("/api/roles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("ADMIN"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("USER"));

        verify(roleService, times(1)).getAllRoles();
    }

    @Test
    void deleteRole() throws Exception {
        doNothing().when(roleService).deleteRole("1");

        mockMvc.perform(delete("/api/roles/1"))
                .andExpect(status().isNoContent());

        verify(roleService, times(1)).deleteRole("1");

        doNothing().when(roleService).deleteRole("2");

        mockMvc.perform(delete("/api/roles/2"))
                .andExpect(status().isNoContent());

        verify(roleService, times(1)).deleteRole("2");
    }
}