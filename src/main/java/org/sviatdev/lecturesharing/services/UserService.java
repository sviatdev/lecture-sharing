package org.sviatdev.lecturesharing.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.sviatdev.lecturesharing.dao.UserDao;
import org.sviatdev.lecturesharing.models.University;
import org.sviatdev.lecturesharing.models.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userDao.findAll());
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
