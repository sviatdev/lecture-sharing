package org.sviatdev.lecturesharing.services;

import org.junit.jupiter.api.Test;
import org.sviatdev.lecturesharing.dao.UserDao;
import org.sviatdev.lecturesharing.exceptions.UserAlreadyExistException;
import org.sviatdev.lecturesharing.exceptions.UserNotFoundException;
import org.sviatdev.lecturesharing.models.Role;
import org.sviatdev.lecturesharing.models.University;
import org.sviatdev.lecturesharing.models.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserDao userDao = mock(UserDao.class);
    private final UserService userService = new UserService(userDao);
    private final User USER_1 = new User(
            1L,
            "login1",
            "password1",
            "name1",
            "surname1",
            25,
            University.NAU,
            Role.USER);

    private final User USER_2 = new User(
            2L,
            "login2",
            "password2",
            "name2",
            "surname2",
            26,
            University.KPI,
            Role.USER);

    private final User USER_WITHOUT_USERNAME = new User(
            3L,
            null,
            "password3",
            "name3",
            "surname3",
            27,
            University.NMU,
            Role.USER);

    private final User USER_WITHOUT_PASSWORD = new User(
            4L,
            "username",
            null,
            "name4",
            "surname4",
            28,
            University.NAU,
            Role.USER);
    private final User USER_WITH_SAME_USERNAME = new User(
            5L,
            "login1",
            "password1",
            "name1",
            "surname1",
            25,
            University.NAU,
            Role.USER);

// TODO: rewrite tests

    @Test
    void getAllUsers_returnListOfUsers() {
        // Given
        var list = List.of(USER_1, USER_2);
        when(userDao.findAll()).thenReturn(list);
        // When
        var result = userService.getAllUsers();
        // Then
        assertEquals(result, list);
    }

    @Test
    void getAllUsers_throwUserNotFoundException() {
        // Given
        when(userDao.findAll()).thenReturn(Collections.emptyList());
        // When/Then
        assertThrows(UserNotFoundException.class, userService::getAllUsers);
    }

    @Test
    void getUserById_returnOptionalUser() {
        // Given
        var id = "1";
        when(userDao.findById(any())).thenReturn(Optional.of(USER_1));
        // When
        var user = userService.getUserById(id);
        // Then
        assertEquals(user, Optional.of(USER_1));
    }

    @Test
    void getUserById_returnEmptyOptional() {
        // Given
        var id = "1";
        when(userDao.findById(any())).thenReturn(Optional.empty());
        // When
        var user = userService.getUserById(id);
        // Then
        assertEquals(user, Optional.empty());
    }

    @Test
    void getUserById_throwIllegalArgumentExceptionIfIdIsString() {
        // Given/When/Then
        assertThrows(IllegalArgumentException.class, () -> userService.getUserById("qwerty"));
    }

    @Test
    void getUserById_throwIllegalArgumentExceptionIfIdIsSymbols() {
        // Given/When/Then
        assertThrows(IllegalArgumentException.class, () -> userService.getUserById("!@#$%^&*()_-+="));
    }

    @Test
    void findByUsername_returnOptionalUser() {
        // Given
        when(userDao.findByUsername(anyString())).thenReturn(Optional.of(USER_1));
        // When
        var result = userService.findByUsername("username");
        // Then
        assertEquals(result, Optional.of(USER_1));
    }

    @Test
    void findByUsername_returnOptionalEmptyList() {
        // Given
        when(userDao.findByUsername(anyString())).thenReturn(Optional.empty());
        // When
        var result = userService.findByUsername("username");
        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void findByUsername_exceptionIfUsernameIsNull() {
        // Given/When/Then
        assertThrows(IllegalArgumentException.class, () -> userService.findByUsername(null));
    }

    @Test
    void findByUsername_exceptionIfUsernameIsEmpty() {
        // Given/When/Then
        assertThrows(IllegalArgumentException.class, () -> userService.findByUsername(""));
    }

    @Test
    void findUsersByUniversity_returnUsers() {
        // Given
        when(userDao.findByUniversity(any(University.class))).thenReturn(List.of(USER_1, USER_2));
        // When
        var result = userService.findUsersByUniversity(University.NAU);
        // Then
        assertEquals(result, List.of(USER_1, USER_2));
    }

    @Test
    void findUsersByUniversity_returnEmptyList() {
        // Given
        when(userDao.findByUniversity(any(University.class))).thenReturn(Collections.emptyList());
        // When
        var result = userService.findUsersByUniversity(University.NAU);
        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void insertUser_saveUser() {
        // Given
        when(userDao.findByUsername(anyString())).thenReturn(Optional.of(USER_1));
        // When
        userService.insertUser(USER_1);
        // Then
        verify(userDao, times(1)).save(any(User.class));
        verify(userDao, times(1)).findByUsername(anyString());
    }

    @Test
    void insertUser_throwIllegalArgumentExceptionIfUserIsNull() {
        // Given/When/Then
        assertThrows(IllegalArgumentException.class, () -> userService.insertUser(null));
    }

    @Test
    void insertUser_throwIllegalArgumentExceptionIfUsernameIsNull() {
        // Given/When/Then
        assertThrows(IllegalArgumentException.class, () -> userService.insertUser(USER_WITHOUT_USERNAME));
    }

    @Test
    void insertUser_throwIllegalArgumentExceptionIfPasswordIsNull() {
        // Given/When/Then
        assertThrows(IllegalArgumentException.class, () -> userService.insertUser(USER_WITHOUT_PASSWORD));
    }

    @Test
    void insertUser_throwUserNotFoundExceptionIfUserAlreadyExist() {
        // Given/When/Then
        assertThrows(UserAlreadyExistException.class, () -> userService.insertUser(USER_WITH_SAME_USERNAME));
    }
}