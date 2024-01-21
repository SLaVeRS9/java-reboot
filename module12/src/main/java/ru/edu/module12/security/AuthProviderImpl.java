package ru.edu.module12.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.edu.module12.entity.User;
import ru.edu.module12.services.UserDetailServiceImpl;

import java.util.Collections;

/**
 * Реализовал через AuthenticationProvider, т.к. более универсальное решение под корп.нужды.
 * Можно и без него (для текущей архитектуры проекта)
 */

@Component
@RequiredArgsConstructor
public class AuthProviderImpl implements AuthenticationProvider {
    private final String ADMIN_LOGIN = "admin";
    private final String ADMIN_PASSWORD = "123456";

    private final UserDetailServiceImpl userDetailService;
    private final PasswordEncoder passwordEncoder;

    //Логика аутентификации
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //Под задание заглушка для аутентификации admin/123456, как дефолтный юзер с доступом
        if(authentication.getPrincipal().toString().equals(ADMIN_LOGIN) &&
                authentication.getCredentials().toString().equals(ADMIN_PASSWORD)) {
            User defaultAdmin = new User();
            defaultAdmin.setName("admin");
            defaultAdmin.setAge(45);
            defaultAdmin.setPassword("123456");
            defaultAdmin.setRole("ROLE_ADMIN");
            UserDetailsImpl userDetails = new UserDetailsImpl(defaultAdmin);
            return new UsernamePasswordAuthenticationToken(userDetails, ADMIN_PASSWORD, userDetails.getAuthorities());
        }

        String userName = authentication.getName();
        UserDetails userDetails = userDetailService.loadUserByUsername(userName);


        //String password = passwordEncoder.encode(authentication.getCredentials().toString());
        String password = authentication.getCredentials().toString();

        if (!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }


    //Для каких сценариев использовать этот AuthenticationProvider
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
