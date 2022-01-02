package fr.manu.picom_api.controller;

import fr.manu.picom_api.model.Role;
import fr.manu.picom_api.model.User;
import fr.manu.picom_api.service.RoleService;
import fr.manu.picom_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final UserService userService;
    private final RoleService roleService;

    @PostConstruct
    private void init() {

        if (roleService.getRoles().isEmpty()) {

            roleService.addRole(new Role(null, "ROLE_USER"));
            roleService.addRole(new Role(null, "ROLE_ADMIN"));
        }

        if (userService.getUsers().isEmpty()) {

            User user = new User("admin", "admin", "admin@gmail.com", "admin", "admin");
            User user1 = new User("user", "user", "user@gmail.com", "user", "user");

            userService.addUser(user);
            userService.addUser(user1);

            roleService.addRoleToUser("admin@gmail.com", "ROLE_ADMIN");
            roleService.addRoleToUser("admin@gmail.com", "ROLE_USER");
            roleService.addRoleToUser("user@gmail.com", "ROLE_USER");
        }
    }

    @GetMapping("/app/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/app/users/{email}")
    public ResponseEntity<User> getUserById(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(userService.findByUserEmail(email));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.addUser(user));
    }
}
