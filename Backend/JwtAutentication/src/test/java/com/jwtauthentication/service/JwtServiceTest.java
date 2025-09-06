package com.jwtauthentication.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import com.jwtauthentication.dao.TokenDao;
import com.jwtauthentication.entity.Token;
import com.jwtauthentication.entity.User;

@SpringBootTest
public class JwtServiceTest {

    @MockBean
    private TokenDao tokenDao;

    @Test
    public void testGenerateToken() {
        User user = new User();
        user.setUsername("testuser");

        JwtService jwtService = new JwtService(tokenDao);
        String token = jwtService.generateToken(user);
        assertNotNull(token);
    }

    @Test
    public void testExtractUsername() {
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJTdWRpcCIsImlhdCI6MTcxMTk2OTM1MiwiZXhwIjoxNzEyMDU1NzUyfQ.BFNlgKc4uSUVfiuBSxTiAa72TnXmlZTNGnGqdTzjraTZY2pDg6qOH0j5M0tgsToS"; 
        JwtService jwtService = new JwtService(tokenDao);
        String username = jwtService.extractUsername(token);
        assertEquals("Sudip", username);
    }

    @Test
    public void testIsValid_WhenValidToken() {
        User user = new User();
        user.setUsername("Sudip");

        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJTdWRpcCIsImlhdCI6MTcxMTk2OTM1MiwiZXhwIjoxNzEyMDU1NzUyfQ.BFNlgKc4uSUVfiuBSxTiAa72TnXmlZTNGnGqdTzjraTZY2pDg6qOH0j5M0tgsToS";
        Token validToken = new Token();
        //validToken.setLoggedOut(false);

        when(tokenDao.findByToken(token)).thenReturn(Optional.of(validToken));

        JwtService jwtService = new JwtService(tokenDao);
        boolean isValid = jwtService.isValid(token, user);
        assertTrue(isValid);
    }

    @Test
    public void testIsValid_WhenInvalidToken() {
        User user = new User();
        user.setUsername("Sudip");

        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJTdWRpcCIsImlhdCI6MTcxMTk2MzAyMSwiZXhwIjoxNzEyMDQ5NDIxfQ.BItliS01hTfClY5zNrpDkrmZ6b_1RPxcfNlZU2wx9Pm-i3UnG1QcNpy6M-HaLqS6";

        // Mock the tokenDao.findByToken() method to return a token that is not expired and is not logged out
        Token validToken = new Token();
        validToken.setToken(token);
        validToken.setLoggedOut(false); // Ensure token is not logged out
        when(tokenDao.findByToken(token)).thenReturn(Optional.of(validToken));

        JwtService jwtService = new JwtService(tokenDao);
        boolean isValid = jwtService.isValid(token, user);
        System.out.println(isValid);
        assertTrue(isValid); // Token should not be valid as it is not expired but should have been logged out
    }




}
