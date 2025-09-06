package com.userservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.userservice.dto.Recipe;
import com.userservice.entities.UserInfo;
import com.userservice.entities.UserInfoDAO;
import com.userservice.exception.InvalidRecipeException;
import com.userservice.exception.InvalidUserException;
import com.userservice.proxy.RecipeProxy;

@SpringBootTest
public class UserServiceImpTest {

    @MockBean
    private UserInfoDAO userInfoDao;

    @MockBean
    private RecipeProxy recipeProxy;

    @Test
    public void testFindByUsername_WhenUserExists() throws InvalidUserException {
        // Given
        String username = "testuser";
        UserInfo user = new UserInfo();
        user.setUsername(username);

        when(userInfoDao.findById(username)).thenReturn(Optional.of(user));

        UserServiceImp userService = new UserServiceImp(userInfoDao);
        //userService.setUserInfoDao(userInfoDao);

        // When
        Optional<UserInfo> foundUser = userService.findByUsername(username);

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals(username, foundUser.get().getUsername());
    }

    @Test
    public void testFindByUsername_WhenUserDoesNotExist() {
        // Given
        String username = "nonexistentuser";

        when(userInfoDao.findById(username)).thenReturn(Optional.empty());

        UserServiceImp userService = new UserServiceImp(userInfoDao);
        //userService.setUserInfoDao(userInfoDao);

        // When & Then
        assertThrows(InvalidUserException.class, () -> userService.findByUsername(username));
    }

    @Test
    public void testSaveRecipe() throws InvalidUserException {
        // Given
        String username = "testuser";
        UserInfo user = new UserInfo();
        user.setUsername(username);

        Recipe recipe = new Recipe();
        recipe.setRecipeName("Test Recipe");

        when(userInfoDao.findById(username)).thenReturn(Optional.of(user));

        UserServiceImp userService = new UserServiceImp(userInfoDao);
        //userService.setUserInfoDao();

        // When
        String result = userService.saveRecipe(recipe, username);

        // Then
        assertNotNull(result);
        assertTrue(result.contains(username));
        assertTrue(result.contains(recipe.getRecipeName()));
    }

    // Add more test cases for other methods similarly

}

