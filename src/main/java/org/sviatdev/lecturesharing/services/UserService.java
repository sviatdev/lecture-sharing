package org.sviatdev.lecturesharing.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.sviatdev.lecturesharing.dao.UserDao;
import org.sviatdev.lecturesharing.models.University;
import org.sviatdev.lecturesharing.models.User;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public ResponseEntity<?> getAllUsers() {
        var users = userDao.findAll();
        return !users.isEmpty()
                ? ResponseEntity.ok(users)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found.");
    }

    public ResponseEntity<User> insertUser(User user) {
        return ResponseEntity.ok(userDao.save(user));
    }

    public ResponseEntity<?> getUserById(Long id) {
        var user = userDao.findById(id);
        return user.isPresent()
                ? ResponseEntity.ok(user)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found.");
    }

    public ResponseEntity<?> findByUsername(String userName) {
        var user = userDao.findByUsername(userName);
        return user != null
                ? ResponseEntity.ok(user)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found.");
    }

    public ResponseEntity<?> findUsersByUniversity(University university) {
        var users = userDao.findUsersByUniversity(university);
        return !users.isEmpty()
                ? ResponseEntity.ok(users)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found.");
    }

    public void removeUser(Long id) {
        userDao.deleteById(id);
    }


}
