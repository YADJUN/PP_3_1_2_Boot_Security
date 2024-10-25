package ru.kata.spring.boot_security.demo.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DefaultCustomizer {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostConstruct
    public void init() {
        //here save default user

       // userService.saveUser(new User(2L,"Bublik",23,"1234",))
    }
}
