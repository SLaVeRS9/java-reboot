package ru.edu.module12.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.edu.module12.entity.User;
import ru.edu.module12.services.UserService;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest({UserController.class})
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private ObjectMapper mapper;

    @BeforeEach
    void setUp(){}

    @AfterEach
    void tearDown(){}

    @Test
    @DisplayName("check receiving all users")
    void getAllUsers() throws Exception {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User(1L, "user1", 11, "pass1", "ROLE_USER"),
                new User(2L, "user2", 22, "pass2", "ROLE_USER"),
                new User(3L, "user3", 33, "pass3", "ROLE_USER"),
                new User(4L, "user4", 44, "pass4", "ROLE_USER")
        ));

        when(userService.getAll()).thenReturn(users);

        mockMvc.perform()
                .andDo(print())

    }

}
