package ru.slavers9.springboottest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.slavers9.springboottest.dto.UserDto;
import ru.slavers9.springboottest.repositiries.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public UserDto getUserByID(Long id) {
        return userRepository.getUserByID(id);
    }

    public void addUser(UserDto userDto) {
        userRepository.addUser(userDto);
    }

    public void updateUser(UserDto userDto) {
        userRepository.updateUser(userDto);
    }

    public void removeUser(Long id) {
        userRepository.removeUser(id);
    }

}
