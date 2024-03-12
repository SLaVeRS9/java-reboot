package ru.edu.services;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.edu.entity.User;
import ru.edu.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RegistrationService registrationService;
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    //Сделал для проверки, что и так можно ограничивать доступ
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(readOnly = false)
    public void add(User user) {
        registrationService.register(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(User user, Long id) {
        User changedUser = userRepository.findById(id).orElseThrow();
        String password = changedUser.getPassword();
        user.setId(id);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
