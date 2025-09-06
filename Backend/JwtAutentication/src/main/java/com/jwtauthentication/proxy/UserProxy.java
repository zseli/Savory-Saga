package com.jwtauthentication.proxy;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jwtauthentication.LoadBalancerConfiguration;
import com.jwtauthentication.entity.Recipe;
import com.jwtauthentication.exception.InvalidRecipeException;
import com.jwtauthentication.exception.InvalidUserException;

@FeignClient(value="USER-SERVICE",url="http://localhost:8081/user")
@LoadBalancerClient(name = "USER-SERVICE",
configuration=LoadBalancerConfiguration.class)
public interface UserProxy {
	
	@GetMapping("/userGreeting/{username}")
    public ResponseEntity<String> userGreeting(@PathVariable("username") String username);
	
	@GetMapping("/findByIngredients/{ingredient1}/{ingredient2}")
	ResponseEntity<List<Recipe>> findByIngredients(@PathVariable String ingredient1,
                                   @PathVariable String ingredient2);
	

    @PostMapping("/addRecipe/{username}")
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe, @PathVariable String username)
            throws InvalidRecipeException, InvalidUserException;

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) throws InvalidUserException;

    @PutMapping("/saveRecipe/{username}/{recipeId}")
    public ResponseEntity<String> saveRecipe(@PathVariable("username") String username,
                                             @PathVariable("recipeId") String recipeId);

    @PutMapping("/updateRecipe/{recipeId}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable String recipeId, @RequestBody Recipe updatedRecipe)
            throws InvalidRecipeException;

    @DeleteMapping("/deleteRecipe/{username}/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable String username, @PathVariable String recipeId) throws InvalidRecipeException;

    @GetMapping("/getById/{recipeId}")
    public ResponseEntity<Recipe> getById(@PathVariable String recipeId) throws InvalidRecipeException;

    @GetMapping("/getAllRecipes")
    public ResponseEntity<List<Recipe>> getAllRecipes();

    @GetMapping("/getRecipeByName/{recipeName}")
    public ResponseEntity<List<Recipe>> getRecipeByName(@PathVariable String recipeName);

    @GetMapping("/getRecipeByType/{type}")
    public ResponseEntity<?> getRecipeByType(@PathVariable String type) throws InvalidRecipeException;

    @GetMapping("/getRecipeByCategory/{category}")
    public ResponseEntity<List<Recipe>> getRecipeByCategory(@PathVariable String category);

    @GetMapping("/getRecipeByAuthor/{author}")
    public ResponseEntity<List<Recipe>> getRecipeByAuthor(@PathVariable String author);

    @GetMapping("/getRecipeByCuisine/{cuisine}")
    public ResponseEntity<List<Recipe>> getRecipeByCuisine(@PathVariable String cuisine);

    @GetMapping("/myProfile/{username}")
    public ResponseEntity<?> seeProfile(@PathVariable("username") String username);

	

}
