package com.recipeservice.service;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
 
import com.recipeservice.entity.Recipe;
import com.recipeservice.exception.InvalidRecipeException;
import com.recipeservice.repository.RecipeRepository;
import com.recipeservice.service.RecipeService;
 
@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {
 
    @Mock
    private RecipeRepository recipeRepository;
 
    @InjectMocks
    private RecipeService recipeService;
 
    private Recipe recipe;
    private List<Recipe> recipeList;
 
    @BeforeEach
    public void setup() {
        // Initialize sample recipe
        recipe = new Recipe("1", "Test Recipe", "Dessert", "Veg", new ArrayList<>(), "Steps", "Indian", "User");
 
        // Initialize sample recipe list
        recipeList = new ArrayList<>();
        recipeList.add(recipe);
    }
 
    /*@Test
    public void testInsertRecipe_ValidRecipe_Success() throws InvalidRecipeException {
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
 
        Recipe savedRecipe = recipeService.insertRecipe(recipe);
 
        assertEquals(recipe, savedRecipe);
        verify(recipeRepository, times(1)).save(recipe);
    }*/
 
    @Test
    public void testUpdateRecipe_ExistingRecipe_Success() throws InvalidRecipeException {
        String recipeId = "1";
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(recipeId)).thenReturn(optionalRecipe);
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
 
        Recipe updatedRecipe = new Recipe("1", "Updated Recipe", "Dessert", "Veg", new ArrayList<>(), "Steps", "Indian", "User");
        Recipe savedRecipe = recipeService.updateRecipe(recipeId, updatedRecipe);
 
        assertEquals(updatedRecipe.getRecipeName(), savedRecipe.getRecipeName());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
 
    @Test
    public void testDeleteRecipe_ExistingRecipe_Success() throws InvalidRecipeException {
        String recipeId = "1";
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(recipeId)).thenReturn(optionalRecipe);
 
        Recipe deletedRecipe = recipeService.deleteRecipe(recipeId);
 
        assertEquals(recipe, deletedRecipe);
        verify(recipeRepository, times(1)).deleteById(recipeId);
    }
 
    @Test
    public void testGetAllRecipes_ReturnsRecipeList_Success() {
        when(recipeRepository.findAll()).thenReturn(recipeList);
 
        List<Recipe> recipes = recipeService.getAllRecipes();
 
        assertEquals(recipeList.size(), recipes.size());
        assertEquals(recipeList.get(0), recipes.get(0));
    }
 
    @Test
    public void testGetRecipeById_ExistingRecipeId_ReturnsRecipe() throws InvalidRecipeException {
        String recipeId = "1";
        Optional<Recipe> optionalRecipe = Optional.of(recipe);
        when(recipeRepository.findById(recipeId)).thenReturn(optionalRecipe);
 
        Recipe retrievedRecipe = recipeService.getRecipeById(recipeId);
 
        assertEquals(recipe, retrievedRecipe);
    }
 
    @Test
    public void testGetRecipeById_NonExistingRecipeId_ThrowsException() {
        String recipeId = "999";
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.empty());
 
        assertThrows(InvalidRecipeException.class, () -> recipeService.getRecipeById(recipeId));
    }
    
    @Test
    public void testGetRecipeByType_ValidType_ReturnsRecipes() throws InvalidRecipeException {
        String type = "Veg";
        when(recipeRepository.findByType(type)).thenReturn(recipeList);
 
        List<Recipe> recipes = recipeService.getRecipeByType(type);
 
        assertEquals(recipeList, recipes);
        verify(recipeRepository, times(1)).findByType(type);
    }
 
    @Test
    public void testGetRecipeByType_InvalidType_ThrowsException() {
        String type = "InvalidType";
 
        assertThrows(InvalidRecipeException.class, () -> recipeService.getRecipeByType(type));
        verify(recipeRepository, never()).findByType(type);
    }
 
    @Test
    public void testGetRecipeByCuisine_ValidCuisine_ReturnsRecipes() {
        List<String> validCuisines = Arrays.asList("Italian", "Chinese", "Indian", "Korean");
 
        for (String cuisine : validCuisines) {
            List<Recipe> expectedRecipes = new ArrayList<>();
            expectedRecipes.add(recipe);
 
            when(recipeRepository.findByCuisine(cuisine)).thenReturn(expectedRecipes);
 
            List<Recipe> recipes = recipeService.getRecipeByCuisine(cuisine);
 
            assertEquals(expectedRecipes, recipes);
            verify(recipeRepository, times(1)).findByCuisine(cuisine);
        }
    }
 
    /*@Test
    public void testGetRecipeByCuisine_InvalidCuisine_ThrowsException() {
        String invalidCuisine = "InvalidCuisine";
 
        assertThrows(InvalidRecipeException.class, () -> recipeService.getRecipeByCuisine(invalidCuisine));
        verify(recipeRepository, never()).findByCuisine(invalidCuisine);
    }*/
 
    @Test
    public void testGetRecipeByCategory_ValidCategory_ReturnsRecipes() {
        List<String> validCategories = Arrays.asList("Dessert", "Snack");
 
        for (String category : validCategories) {
            List<Recipe> expectedRecipes = new ArrayList<>();
            expectedRecipes.add(recipe);
 
            when(recipeRepository.findByCategory(category)).thenReturn(expectedRecipes);
 
            List<Recipe> recipes = recipeService.getRecipeByCategory(category);
 
            assertEquals(expectedRecipes, recipes);
            verify(recipeRepository, times(1)).findByCategory(category);
        }
    }
 
   @Test
    public void testGetRecipeByCategory_InvalidCategory_ThrowsException() {
        String invalidCategory = "InvalidCategory";
 
        assertThrows(InvalidRecipeException.class, () -> recipeService.getRecipeByCategory(invalidCategory));
        verify(recipeRepository, never()).findByCategory(invalidCategory);
    }
 
 
}
