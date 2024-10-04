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
    public ResponseEntity<?> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/all/university")
    public ResponseEntity<?> getUsersByUniversity(@RequestParam String university) {
        try {
            var uni = University.valueOf(university.toUpperCase());
            return userService.findUsersByUniversity(uni);
        } catch (IllegalArgumentException e) {
            //TODO: add logger
            return ResponseEntity.badRequest().body("Invalid university value [" + university + "]");
        }
    }

    @GetMapping("/get/id")
    public ResponseEntity<?> getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/get/username")
    public ResponseEntity<?> findByUsername(@RequestParam String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/insert/{user}")
    public ResponseEntity<?> insertUser(@PathVariable User user) {
        return userService.insertUser(user);
    }

    @DeleteMapping(value = "/delete/id")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        return userService.removeUser(id);
    }
}
