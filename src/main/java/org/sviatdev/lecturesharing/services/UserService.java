package org.sviatdev.lecturesharing.services;

import org.springframework.stereotype.Service;
import org.sviatdev.lecturesharing.dao.UserDao;
import org.sviatdev.lecturesharing.exceptions.UserAlreadyExistException;
import org.sviatdev.lecturesharing.exceptions.UserNotFoundException;
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

    public List<User> getAllUsers() throws UserNotFoundException {
        var users = userDao.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }
        return users;
    }

    public Optional<User> getUserById(String id) {
        if(!id.matches("^[0-9]+$")) {
            throw new IllegalArgumentException();
        }
        return userDao.findById(Long.parseLong(id));
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
        } else if (!isUserAlreadyExist(user)) {
            throw new UserAlreadyExistException();
        }
        userDao.save(user);
    }

    public Optional<User> findByUsername(String userName) {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return userDao.findByUsername(userName);
    }

    public void removeUser(String id) throws UserNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException();
        }

        var user = getUserById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        userDao.deleteById(Long.parseLong(id));
    }

    private boolean isUserAlreadyExist(User user) {
        return userDao.findByUsername(user.getUsername()).isPresent();
    }
}
