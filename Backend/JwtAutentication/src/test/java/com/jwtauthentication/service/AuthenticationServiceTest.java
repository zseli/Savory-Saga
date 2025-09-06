package com.jwtauthentication.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jwtauthentication.dao.TokenDao;
import com.jwtauthentication.dao.UserDao;
import com.jwtauthentication.dao.UserInfoDao;
import com.jwtauthentication.entity.AuthenticationResponse;
import com.jwtauthentication.entity.Roles;
import com.jwtauthentication.entity.Token;
import com.jwtauthentication.entity.User;

@SpringBootTest
public class AuthenticationServiceTest {

    @MockBean
    private UserDao userDao;

    @MockBean
    private UserInfoDao userInfoDao;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private TokenDao tokenDao;

    @Test
    public void testRegister() {
        // Given
        User request = new User();
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setRole(Roles.USER);

        when(userDao.existsByUsername("testuser")).thenReturn(false);

        User savedUser = new User();
        savedUser.setUsername("testuser");
        savedUser.setEmail("test@example.com");
        savedUser.setPassword("encodedPassword");
        savedUser.setRole(Roles.USER);

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userDao.save(any(User.class))).thenReturn(savedUser);

        String token = "generatedToken";
        when(jwtService.generateToken(savedUser)).thenReturn(token);

        // When
        AuthenticationService authenticationService = new AuthenticationService(userDao, userInfoDao, passwordEncoder, jwtService, authenticationManager, tokenDao);
        AuthenticationResponse response = authenticationService.register(request);

        // Then
        assertEquals(token, response.getToken());
        assertEquals("testuser", response.getUsername());
    }

    @Test
    public void testAuthenticate() {
        // Given
        User request = new User();
        request.setUsername("testuser");
        request.setPassword("password");

        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setRole(Roles.USER);

        when(userDao.findByUsername("testuser")).thenReturn(Optional.of(user));

        String token = "generatedToken";
        when(jwtService.generateToken(user)).thenReturn(token);

        // When
        AuthenticationService authenticationService = new AuthenticationService(userDao, userInfoDao, passwordEncoder, jwtService, authenticationManager, tokenDao);
        AuthenticationResponse response = authenticationService.authenticate(request);

        // Then
        assertEquals(token, response.getToken());
        assertEquals("testuser", response.getUsername());
    }

}

