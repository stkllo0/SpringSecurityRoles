package ru.kata.spring.boot_security.demo.init;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DbInit {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;
    private final UserRepository userRepository;

    public DbInit(UserServiceImpl userService, RoleServiceImpl roleService,
                  UserRepository userRepository) {
        this.userService = userService;
        this.roleService = roleService;

        this.userRepository = userRepository;
    }

    @PostConstruct
    private void postConstruct() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        User user = new User("user", "userovich", "user", 500, Set.of(roleUser));
        User admin = new User("admin", "adminovich", "admin", 700, Set.of(roleAdmin));
        userService.addUser(user);
        userService.addUser(admin);
    }
}
