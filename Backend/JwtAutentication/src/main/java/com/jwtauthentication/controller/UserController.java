package com.jwtauthentication.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.jwtauthentication.entity.Recipe;
import com.jwtauthentication.exception.InvalidRecipeException;
import com.jwtauthentication.exception.InvalidUserException;
import com.jwtauthentication.proxy.UserProxy;
import com.jwtauthentication.service.AuthenticationService;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserProxy userProxy;
	@Autowired
	private AuthenticationService authService;
	
	
	//private String currentUser=authService.getCurrentUser().getUsername();
	
	@GetMapping(value = "/userGreeting")
	public ResponseEntity<String> userGreeting(){
		return userProxy.userGreeting(authService.getCurrentUser().getUsername());
	}
	
	@GetMapping("/findByIngredients/{ingredient1}/{ingredient2}")
	ResponseEntity<?> findByIngredients(@PathVariable String ingredient1,
            @PathVariable String ingredient2){
		return userProxy.findByIngredients(ingredient1, ingredient2);
	}
	
	@PostMapping("/addRecipe")
	public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) throws InvalidRecipeException, InvalidUserException{
		ResponseEntity<?> res=userProxy.addRecipe(recipe, authService.getCurrentUser().getUsername());
		System.out.println(res);
		return res;
	}
	
	@GetMapping("/getUserByUsername")
	public ResponseEntity<?> getUserByUsername() throws InvalidUserException {
	        return userProxy.getUserByUsername(authService.getCurrentUser().getUsername());
	 }

	    @PutMapping("/saveRecipe/{recipeId}")
	    public ResponseEntity<String> saveRecipe(@PathVariable("recipeId") String recipeId) {
	        return userProxy.saveRecipe(authService.getCurrentUser().getUsername(), recipeId);
	    }

	    @PutMapping("/updateRecipe/{recipeId}")
	    public ResponseEntity<Recipe> updateRecipe(@PathVariable String recipeId, @RequestBody Recipe updatedRecipe)
	            throws InvalidRecipeException {
	        return userProxy.updateRecipe(recipeId, updatedRecipe);
	    }

	    @DeleteMapping("/deleteRecipe/{recipeId}")
	    public ResponseEntity<String> deleteRecipe(@PathVariable String recipeId) throws InvalidRecipeException {
	        return userProxy.deleteRecipe(authService.getCurrentUser().getUsername(), recipeId);
	    }

	    @GetMapping("/getById/{recipeId}")
	    public ResponseEntity<Recipe> getById(@PathVariable String recipeId) throws InvalidRecipeException {
	        return userProxy.getById(recipeId);
	    }

	    @GetMapping("/getAllRecipes")
	    public ResponseEntity<List<Recipe>> getAllRecipes() {
	        return userProxy.getAllRecipes();
	    }

	    @GetMapping("/getRecipeByName/{recipeName}")
	    public ResponseEntity<List<Recipe>> getRecipeByName(@PathVariable String recipeName) {
	        return userProxy.getRecipeByName(recipeName);
	    }

	    @GetMapping("/getRecipeByType/{type}")
	    public ResponseEntity<?> getRecipeByType(@PathVariable String type) throws InvalidRecipeException {
	        return userProxy.getRecipeByType(type);
	    }

	    @GetMapping("/getRecipeByCategory/{category}")
	    public ResponseEntity<List<Recipe>> getRecipeByCategory(@PathVariable String category) {
	        return userProxy.getRecipeByCategory(category);
	    }

	    @GetMapping("/getRecipeByAuthor/{author}")
	    public ResponseEntity<List<Recipe>> getRecipeByAuthor(@PathVariable String author) {
	        return userProxy.getRecipeByAuthor(author);
	    }

	    @GetMapping("/getRecipeByCuisine/{cuisine}")
	    public ResponseEntity<List<Recipe>> getRecipeByCuisine(@PathVariable String cuisine) {
	        return userProxy.getRecipeByCuisine(cuisine);
	    }

	    @GetMapping("/myProfile")
	    public ResponseEntity<?> seeProfile() {
	        return userProxy.seeProfile(authService.getCurrentUser().getUsername());
	    }
	
	@GetMapping("/userHome")
	public ResponseEntity<String> demo(){
		return ResponseEntity.ok("Hello you are authenticated");
	}
	
	
	
	
	

}
