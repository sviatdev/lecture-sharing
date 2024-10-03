package org.sviatdev.lecturesharing.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.sviatdev.lecturesharing.dao.UserDao;
import org.sviatdev.lecturesharing.models.University;
import org.sviatdev.lecturesharing.models.User;

import java.util.Optional;

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

    public ResponseEntity<Optional<User>> getUserById(Long id) {
        return ResponseEntity.ok(userDao.findById(id));
    }

    public User findByUsername(String userName) {
        return userDao.findByUsername(userName);
    }

    public ResponseEntity<?> findUsersByUniversity(University university) {
        return ResponseEntity.ok(userDao.findUsersByUniversity(university));
    }

    public void removeUser(Long id) {
        userDao.deleteById(id);
    }


}
