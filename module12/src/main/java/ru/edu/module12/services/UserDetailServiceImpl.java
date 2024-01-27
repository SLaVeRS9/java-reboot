package ru.edu.module12.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.edu.module12.entity.User;
import ru.edu.module12.repositories.UserRepository;
import ru.edu.module12.security.UserDetailsImpl;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;


    //Логика получения пользователя
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }

        return new UserDetailsImpl(user.get());
    }
}
