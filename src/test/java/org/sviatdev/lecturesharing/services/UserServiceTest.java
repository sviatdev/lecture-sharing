package org.sviatdev.lecturesharing.services;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.sviatdev.lecturesharing.dao.UserDao;
import org.sviatdev.lecturesharing.models.Role;
import org.sviatdev.lecturesharing.models.University;
import org.sviatdev.lecturesharing.models.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


class UserServiceTest {

    private final UserDao userDao = mock(UserDao.class);
    private final UserService userService = new UserService(userDao);

    private static final User USER = new User(1L, "login", "password", "name", "surname", 25, University.NAU, Role.USER);
    @Test
    void getAllUsers_success() {
        // Given
        when(userDao.findAll()).thenReturn(List.of(USER));
        // When
        var result = userService.getAllUsers();
        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(List.of(USER), result.getBody());
        verify(userDao, times(1)).findAll();
    }

    @Test
    void getAllUsers_error() {
        // Given
        when(userDao.findAll()).thenReturn(Collections.emptyList());
        // When
        var result = userService.getAllUsers();
        // Then
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("No users found.", result.getBody());
        verify(userDao, times(1)).findAll();
    }

    @Test
    void getUserById_success() {
        // Given
        when(userDao.findById(1L)).thenReturn(Optional.of(USER));
        // When
        var result = userService.getUserById(1L);
        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Optional.of(USER), result.getBody());
        verify(userDao, times(1)).findById(1L);
    }

    @Test
    void getUserById_error() {
        // Given
        when(userDao.findById(1L)).thenReturn(Optional.empty());
        // When
        var result = userService.getUserById(1L);
        // Then
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("No users found.", result.getBody());
        verify(userDao, times(1)).findById(1L);
    }
}