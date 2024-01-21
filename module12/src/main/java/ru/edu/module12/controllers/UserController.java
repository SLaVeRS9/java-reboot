package ru.edu.module12.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.edu.module12.entity.User;

import java.util.List;

public interface UserController {
    String getAll(Model model);
    String getById(Long id, Model model);
    String addForm(User user);
    String add(User user);
    String edit(User user, Long id);
    String update(User user, Long id);
    String deleteById(Long id);
}
