package ru.slavers9.springboottest.controllers;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.slavers9.springboottest.dto.UserDto;
import ru.slavers9.springboottest.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserByID(@PathVariable Long id) {
        return userService.getUserByID(id);
    }

    @PostMapping("/add")
    public void addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PatchMapping("/edit")
    public void updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
    }

    @DeleteMapping("/remove")
    public void removeUser(@PathParam("id") Long id) {
        userService.removeUser(id);
    }

}
