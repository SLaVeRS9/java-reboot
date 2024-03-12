package ru.edu.controllers;

import org.springframework.ui.Model;
import ru.edu.entity.User;

public interface UserController {
    String getAll(Model model);
    String getById(Long id, Model model);
    String addForm(User user);
    String add(User user);
    String edit(User user, Long id);
    String update(User user, Long id);
    String deleteById(Long id);
}
