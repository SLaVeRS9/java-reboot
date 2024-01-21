package ru.edu.module12.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.edu.module12.entity.User;
import ru.edu.module12.services.RegistrationService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final RegistrationService registrationService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
    @GetMapping("/register")
    public String register(@ModelAttribute("user") User user, BindingResult bindingResult) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user) {
        registrationService.register(user);
        return "redirect:/auth/login";
    }
}
