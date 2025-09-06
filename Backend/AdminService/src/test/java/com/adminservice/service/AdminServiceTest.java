package com.adminservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adminservice.dao.UserDao;
import com.adminservice.dao.UserInfoDao;
import com.adminservice.entity.Roles;
import com.adminservice.entity.User;
import com.adminservice.entity.UserInfo;


@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private UserInfoDao userInfoDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private AdminService adminService;

    @Test
    public void testGetAllUsers() {
        // Given
        List<UserInfo> users = new ArrayList<>();
        users.add(new UserInfo());
        users.add(new UserInfo());

        when(userInfoDao.findAll()).thenReturn(users);

        // When
        List<UserInfo> result = adminService.getAllUsers();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    public void testChangeUserRole() {
        // Given
        String username = "testuser";
        User user = new User();
        user.setUsername(username);
        user.setRole(Roles.USER);
        Optional<User> optionalUser = Optional.of(user);

        when(userDao.findById(username)).thenReturn(optionalUser);
        when(userDao.save(any(User.class))).thenReturn(user);

        // When
        adminService.changeUserRole(username);

        // Then
        assertEquals(Roles.ADMIN, user.getRole());
    }

    // Add more test cases for other methods similarly

}

