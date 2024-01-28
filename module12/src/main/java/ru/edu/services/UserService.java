package ru.edu.services;

import ru.edu.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long id);
    void add(User user);
    void update(User user, Long id);
    void deleteById(Long id);
}
