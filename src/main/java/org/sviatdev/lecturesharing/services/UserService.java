package org.sviatdev.lecturesharing.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.sviatdev.lecturesharing.dao.UserDao;
import org.sviatdev.lecturesharing.exceptions.UserNotFoundException;
import org.sviatdev.lecturesharing.models.University;
import org.sviatdev.lecturesharing.models.User;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() throws UserNotFoundException {
        var users = userDao.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }
        return users;
    }

    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }

    public List<User> findUsersByUniversity(University university) {
        if (university == null) {
            throw new IllegalArgumentException();
        }
        return userDao.findByUniversity(university);
    }

    public void insertUser(User user) throws UserNotFoundException {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new IllegalArgumentException();
        } else if (!isUserExist(user)) {
            throw new UserNotFoundException();
        }
        userDao.save(user);
    }

    public ResponseEntity<?> findByUsername(String userName) {
        var user = userDao.findByUsername(userName);
        return user.isPresent()
                ? ResponseEntity.ok(user)
                : ResponseEntity.status(NOT_FOUND).body("No user found.");
    }

    public void removeUser(Long id) throws UserNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException();
        }

        var user = getUserById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        userDao.deleteById(id);
    }

    private boolean isUserExist(User user) {
        return userDao.findByUsername(user.getUsername()).isPresent();
    }
}
