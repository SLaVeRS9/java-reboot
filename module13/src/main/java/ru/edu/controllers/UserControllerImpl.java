package ru.edu.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.edu.entity.User;
import ru.edu.services.UserService;

import javax.servlet.annotation.WebFilter;


@Controller
@RequestMapping("/users")
@WebFilter("/users")
@AllArgsConstructor
public class UserControllerImpl implements UserController{
    private UserService userService;
    @Override
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users/users";
    }

    @Override
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/user";
    }

    @Override
    @GetMapping("/add")
    public String addForm(@ModelAttribute("user") User user) {
        return "users/add";
    }

    @Override
    @PostMapping
    public String add(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @Override
    @GetMapping("/{id}/edit")
    public String edit(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.getById(id);
        return "/users/edit";
    }

    @Override
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(user, id);
        return "redirect:/users";
    }

    @Override
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
