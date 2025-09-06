package com.userservice.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.userservice.exception.InvalidRecipeException;
import com.userservice.dto.AuthenticationResponse;
import com.userservice.dto.Recipe;
import com.userservice.entities.UserInfo;
import com.userservice.exception.InvalidUserException;
import com.userservice.proxy.RecipeProxy;
import com.userservice.service.UserServiceImp;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImp userService;
	
	@Autowired
	private RecipeProxy recipeProxy;
	
	@GetMapping("/userGreeting/{username}")
	public ResponseEntity<String> repGreeting(@PathVariable("username") String username) {
	    return recipeProxy.repGreeting(username);
	}	
	
	@GetMapping("/findByIngredients/{ingredient1}/{ingredient2}")
    public ResponseEntity<List<Recipe>> findByIngredients(@PathVariable String ingredient1,
                                                           @PathVariable String ingredient2) {
			return recipeProxy.findByIngredients(ingredient1, ingredient2);
    }
	

	@GetMapping("/allUsers")
	public ResponseEntity<?> getAllUsers(){
		return ResponseEntity.ok(userService.findAllUsers());
	}
	
	
	
	@PostMapping("/addRecipe/{username}")
	public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe, @PathVariable String username) 
			throws InvalidRecipeException, InvalidUserException{
	    try {
	    	recipe.setAuthor(username);
	        ResponseEntity<Recipe> response = recipeProxy.addRecipe(recipe);
	        System.out.println(response);
	        if (response.getStatusCode() == HttpStatus.CREATED && response.getBody() != null) {
	            Recipe addedRecipe = response.getBody();
	            userService.addRecipe(addedRecipe);
	            return ResponseEntity.ok(addedRecipe);
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add recipe");
	        }
	    } catch (InvalidRecipeException | InvalidUserException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}

	
	
	@GetMapping("/getUserByUsername/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable String username) throws InvalidUserException{
		try {
			return ResponseEntity.ok(userService.findByUsername(username));
		}catch(InvalidUserException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/saveRecipe/{username}/{recipeId}")
	public ResponseEntity<String> saveRecipe(@PathVariable("username") String username,
	        @PathVariable("recipeId") String recipeId) {
	    try {
	        ResponseEntity<Recipe> rep = recipeProxy.getById(recipeId);
	        if (rep.getStatusCode() == HttpStatus.OK && rep.getBody() != null) {
	            Recipe recipe = rep.getBody();
	            String res = userService.saveRecipe(recipe, username);
	            return ResponseEntity.ok(res);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recipe not found");
	        }
	    } catch (InvalidUserException | InvalidRecipeException e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}
	
	
		
	@PutMapping("/updateRecipe/{recipeId}")
	public ResponseEntity<Recipe> updateRecipe(@PathVariable String recipeId, @RequestBody Recipe updatedRecipe) 
    		throws InvalidRecipeException {
		return recipeProxy.updateRecipe(recipeId, updatedRecipe);
	}

    @DeleteMapping("/deleteRecipe/{username}/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable String username ,@PathVariable String recipeId) throws InvalidRecipeException{
		try {
	        ResponseEntity<Recipe> rep = recipeProxy.deleteRecipe(recipeId);
	        if (rep.getStatusCode() == HttpStatus.OK) {
	        	userService.deleteRecipe(username,rep.getBody());
	            return ResponseEntity.ok("Deletion Successful");
	        } else {
	            // Handle other status codes if needed
	            return ResponseEntity.status(rep.getStatusCode()).body("Deletion failed");
	        }
	    } catch (InvalidRecipeException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    } catch (Exception e) {
	        // Handle other exceptions if needed
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
		
	}

    @GetMapping("/getById/{recipeId}")
    public ResponseEntity<Recipe> getById(@PathVariable String recipeId) throws InvalidRecipeException{
		return recipeProxy.getById(recipeId);
	}

    @GetMapping("/getAllRecipes")
    public ResponseEntity<List<Recipe>> getAllRecipes(){
		return recipeProxy.getAllRecipes();
	}

    @GetMapping("/getRecipeByName/{recipeName}")
    public ResponseEntity<List<Recipe>> getRecipeByName(@PathVariable String recipeName){
		return recipeProxy.getRecipeByName(recipeName);
	}

    @GetMapping("/getRecipeByType/{type}")
    public ResponseEntity<?> getRecipeByType(@PathVariable String type) throws InvalidRecipeException{
		return recipeProxy.getRecipeByType(type);
	}

    @GetMapping("/getRecipeByCategory/{category}")
    public ResponseEntity<List<Recipe>> getRecipeByCategory(@PathVariable String category){
		return recipeProxy.getRecipeByCategory(category);
	}

    @GetMapping("/getRecipeByAuthor/{author}")
    public ResponseEntity<List<Recipe>> getRecipeByAuthor(@PathVariable String author){
		return recipeProxy.getRecipeByAuthor(author);
	}
    @GetMapping("/getRecipeByCuisine/{cuisine}")
    public ResponseEntity<List<Recipe>> getRecipeByCuisine(@PathVariable String cuisine){
		return recipeProxy.getRecipeByCuisine(cuisine);
	}
    
    
    @GetMapping("/myProfile/{username}")
    public ResponseEntity<?> seeProfile(@PathVariable("username") String username){
    	return ResponseEntity.ok(userService.seeProfile(username));
    }

}
