package org.sviatdev.lecturesharing.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sviatdev.lecturesharing.models.University;
import org.sviatdev.lecturesharing.models.User;
import org.sviatdev.lecturesharing.services.UserService;

import java.util.List;
import java.util.Optional;

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
            return ResponseEntity.ok(userService.getUserById(id));
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/university")
    public ResponseEntity<List<User>> getUsersByUniversity(@RequestParam String name) {
        var university = University.valueOf(name.toUpperCase());
        return ResponseEntity.ok(userService.findUsersByUniversity(university));
    }

    @GetMapping("/get")
    public ResponseEntity<Optional<User>> findByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @PostMapping("/insert/{user}")
    public void insertUser(@PathVariable User user) {
        userService.insertUser(user);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }
}
