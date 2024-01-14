package ru.slavers9.springboottest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.slavers9.springboottest.dto.UserDto;
import ru.slavers9.springboottest.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest({UserController.class})
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {}

    @AfterEach
    void tearDown() {}

    @Test
    @DisplayName("check receiving all users")
    void getAllUsers() throws Exception {
        List<UserDto> users = new ArrayList<>(Arrays.asList(
                new UserDto(Long.getLong("1") , "Name-1", 17),
                new UserDto(Long.getLong("2"), "Name-1", 17),
                new UserDto(Long.getLong("3"), "Name-1", 17)
        ));

        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)));
    }

    @Test
    @DisplayName("check receiving user by id")
    void getUserByID() throws Exception {
        UserDto user = new UserDto(Long.getLong("1") , "Name-1", 17);

        when(userService.getUserByID(anyLong())).thenReturn(user);

        mockMvc.perform(get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    @DisplayName("check updating user")
    void updateUser() throws Exception {
        UserDto user = new UserDto(1L, "Name-1", 17);
        doNothing().when(userService).updateUser(any(UserDto.class));
        userService.updateUser(user);
        verify(userService).updateUser(any(UserDto.class));

        mockMvc.perform(patch("/users/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("check deleting user")
    void removeUser() throws Exception {
        doNothing().when(userService).removeUser(anyLong());
        userService.removeUser(anyLong());
        verify(userService).removeUser(anyLong());

        mockMvc.perform(delete("/users/remove"))
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("check adding user")
    void addUser() throws Exception {
        UserDto user = new UserDto(1L, "Name-1", 17);
        doNothing().when(userService).addUser(any(UserDto.class));
        userService.addUser(user);
        verify(userService).addUser(any(UserDto.class));

        mockMvc.perform(post("/users/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(jsonPath("$").doesNotExist())
                .andExpect(status().isOk());
    }
}
