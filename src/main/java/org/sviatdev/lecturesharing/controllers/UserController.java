package org.sviatdev.lecturesharing.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sviatdev.lecturesharing.models.University;
import org.sviatdev.lecturesharing.models.User;
import org.sviatdev.lecturesharing.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    //TODO: add logger
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getUsers(@RequestParam(required = false) Long id) {
        if (id != null) {
            return userService.getUserById(id);
        }
        return userService.getAllUsers();
    }

    @GetMapping("/university")
    public ResponseEntity<?> getUsersByUniversity(@RequestParam String name) {
        try {
            var uni = University.valueOf(name.toUpperCase());
            return userService.findUsersByUniversity(uni);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid university value [" + name + "]");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> findByUsername(@RequestParam String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/insert/{user}")
    public ResponseEntity<?> insertUser(@PathVariable User user) {
        return userService.insertUser(user);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        return userService.removeUser(id);
    }
}
