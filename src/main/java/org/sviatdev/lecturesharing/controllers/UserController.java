package org.sviatdev.lecturesharing.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sviatdev.lecturesharing.models.University;
import org.sviatdev.lecturesharing.models.User;
import org.sviatdev.lecturesharing.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/university={university}/all")
    public ResponseEntity<?> getUsersByUniversity(@PathVariable String university) {
        return userService.findUsersByUniversity(University.valueOf(university));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/get/")
    public ResponseEntity<?> findByUsername(String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/insert/{user}")
    public ResponseEntity<?> insertUser(@PathVariable User user) {
        return userService.insertUser(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.removeUser(id);
    }
}
