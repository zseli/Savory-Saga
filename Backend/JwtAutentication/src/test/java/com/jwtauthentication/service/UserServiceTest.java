package com.jwtauthentication.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jwtauthentication.dao.UserDao;
import com.jwtauthentication.entity.User;


@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserDao userDao;

    @Test
    public void testLoadUserByUsername_WhenUserExists() {
        // Given
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        user.setPassword("password");

        when(userDao.findByUsername(username)).thenReturn(Optional.of(user));

        UserService userService = new UserService(userDao);

        // When
        UserDetails userDetails = userService.loadUserByUsername(username);

        // Then
        assertEquals(username, userDetails.getUsername());
        // Add more assertions as needed
    }

    @Test
    public void testLoadUserByUsername_WhenUserDoesNotExist() {
        // Given
        String username = "nonexistentuser";

        when(userDao.findByUsername(username)).thenReturn(Optional.empty());

        UserService userService = new UserService(userDao);

        // When & Then
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(username));
    }

    // Add more test cases as needed

}

