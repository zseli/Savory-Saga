package com.recipeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.recipeservice.entity.Recipe;
import com.recipeservice.exception.InvalidRecipeException;
import com.recipeservice.service.RecipeService;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping("/repGreeting/{username}")
	public ResponseEntity<String> recipeGreeting(@PathVariable("username") String username){
		String res="Hello from recipe service by " +username;
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/addRecipe")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) throws InvalidRecipeException {
        Recipe savedRecipe = recipeService.insertRecipe(recipe);
        if (savedRecipe != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}
	
	@GetMapping("/byIngredients/{ingredient1}/{ingredient2}")
    public ResponseEntity<List<Recipe>> findByIngredients(@PathVariable String ingredient1,
                                                           @PathVariable String ingredient2) {
        try {
            List<Recipe> recipes = recipeService.findByIngredients(ingredient1, ingredient2);
            return ResponseEntity.ok(recipes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
	@PutMapping("/updateRecipe/{recipeId}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable String recipeId, @RequestBody Recipe updatedRecipe) throws InvalidRecipeException {
        Recipe updated = recipeService.updateRecipe(recipeId, updatedRecipe);
        return ResponseEntity.ok(updated);
    }
	
	@DeleteMapping("/deleteRecipe/{recipeId}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable String recipeId) throws InvalidRecipeException {
        Recipe rep=recipeService.deleteRecipe(recipeId);
        return ResponseEntity.status(HttpStatus.OK).body(rep);
    }
	
	@GetMapping("/getById/{recipeId}")
    public ResponseEntity<Recipe> getById(@PathVariable String recipeId) throws InvalidRecipeException {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        return ResponseEntity.status(HttpStatus.OK).body(recipe);
    }
	
	 @GetMapping("/getAllRecipes")
	    public ResponseEntity<List<Recipe>> getAllRecipes() {
	        List<Recipe> recipes = recipeService.getAllRecipes();
	        return ResponseEntity.status(HttpStatus.OK).body(recipes);
	    }
	 
	 @GetMapping("/getRecipeByName/{recipeName}")
	    public ResponseEntity<List<Recipe>> getRecipeByName(@PathVariable String recipeName) {
	        List<Recipe> recipes = recipeService.getRecipeByName(recipeName);
	        return ResponseEntity.status(HttpStatus.OK).body(recipes);
	    }
	 
	 @GetMapping("/getRecipeByType/{type}")
	    public ResponseEntity<?> getRecipeByType(@PathVariable String type) throws InvalidRecipeException {
		 try {
	            List<Recipe> recipes = recipeService.getRecipeByType(type);
	            return ResponseEntity.ok(recipes);
	        } catch (InvalidRecipeException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	        }
	    }
	 

	 @GetMapping("/getRecipeByCategory/{category}")
	    public ResponseEntity<List<Recipe>> getRecipeByCategory(@PathVariable String category) {
	        List<Recipe> recipes = recipeService.getRecipeByCategory(category);
	        return ResponseEntity.status(HttpStatus.OK).body(recipes);
	    }
	 
	 @GetMapping("/getRecipeByCuisine/{cuisine}")
	    public ResponseEntity<List<Recipe>> getRecipeByCuisine(@PathVariable String cuisine) {
	        List<Recipe> recipes = recipeService.getRecipeByCuisine(cuisine);
	        return ResponseEntity.status(HttpStatus.OK).body(recipes);
	    }
	 
	 @GetMapping("/getRecipeByAuthor/{author}")
	    public ResponseEntity<List<Recipe>> getRecipeByAuthor(@PathVariable String author) {
	        List<Recipe> recipes = recipeService.getRecipeByAuthor(author);
	        return ResponseEntity.status(HttpStatus.OK).body(recipes);
	    }
	 
	 
}
