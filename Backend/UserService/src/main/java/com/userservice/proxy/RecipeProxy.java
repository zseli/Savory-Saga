package com.userservice.proxy;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.userservice.LoadBalancerConfiguration;
import com.userservice.dto.Recipe;
import com.userservice.exception.InvalidRecipeException;

@FeignClient(value="RECIPE-SERVICE",url="http://localhost:8084/recipe")
@LoadBalancerClient(name = "RECIPE-SERVICE",
configuration=LoadBalancerConfiguration.class)
public interface RecipeProxy {
	
	@GetMapping("/repGreeting/{username}")
    ResponseEntity<String> repGreeting(@PathVariable("username") String username);	
	
	@GetMapping("/byIngredients/{ingredient1}/{ingredient2}")
	ResponseEntity<List<Recipe>> findByIngredients(@PathVariable String ingredient1,
                                   @PathVariable String ingredient2);
	
	@PostMapping("/addRecipe")
	ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) throws InvalidRecipeException;
	
	@PutMapping("/updateRecipe/{recipeId}")
    ResponseEntity<Recipe> updateRecipe(@PathVariable String recipeId, @RequestBody Recipe updatedRecipe) throws InvalidRecipeException;

    @DeleteMapping("/deleteRecipe/{recipeId}")
    ResponseEntity<Recipe> deleteRecipe(@PathVariable String recipeId) throws InvalidRecipeException;

    @GetMapping("/getById/{recipeId}")
    ResponseEntity<Recipe> getById(@PathVariable String recipeId) throws InvalidRecipeException;

    @GetMapping("/getAllRecipes")
    ResponseEntity<List<Recipe>> getAllRecipes();

    @GetMapping("/getRecipeByName/{recipeName}")
    ResponseEntity<List<Recipe>> getRecipeByName(@PathVariable String recipeName);

    @GetMapping("/getRecipeByType/{type}")
    ResponseEntity<?> getRecipeByType(@PathVariable String type) throws InvalidRecipeException;

    @GetMapping("/getRecipeByCategory/{category}")
    ResponseEntity<List<Recipe>> getRecipeByCategory(@PathVariable String category);

    @GetMapping("/getRecipeByAuthor/{author}")
    ResponseEntity<List<Recipe>> getRecipeByAuthor(@PathVariable String author);
    
    @GetMapping("/getRecipeByCuisine/{cuisine}")
    ResponseEntity<List<Recipe>> getRecipeByCuisine(@PathVariable String cuisine);
}
