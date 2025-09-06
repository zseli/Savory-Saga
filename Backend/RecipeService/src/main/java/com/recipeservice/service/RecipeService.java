package com.recipeservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recipeservice.entity.Recipe;
import com.recipeservice.exception.InvalidRecipeException;
import com.recipeservice.repository.RecipeRepository;

@Service
public class RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;
	
	public Recipe inserteeRecipe(Recipe recipe,String username) throws InvalidRecipeException {
		if(recipe.getIngredients().size()<3) {
			throw new InvalidRecipeException("Invalid number of Ingredients");
		}
		recipe.setAuthor(username);
		recipeRepository.save(recipe);
		System.out.println(recipe.getRecipeName()+" added successfully");
		return recipe;
	}
	
	public List<Recipe> findByIngredients(String in1, String in2) {
        return recipeRepository.findByIngredients(in1, in2);
    }
	
	public Recipe insertRecipe(Recipe recipe) throws InvalidRecipeException {
		if(recipe.getIngredients().size()<3) {
			throw new InvalidRecipeException("Invalid number of Ingredients");
		}
		recipeRepository.save(recipe);
		System.out.println(recipe.getRecipeName()+" added successfully");
		return recipe;
	}
	
	public Recipe updateRecipe(String recipeId, Recipe updatedRecipe) throws InvalidRecipeException {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if (optionalRecipe.isPresent()) {
            Recipe existingRecipe = optionalRecipe.get();
            existingRecipe.setRecipeName(updatedRecipe.getRecipeName());
            existingRecipe.setCategory(updatedRecipe.getCategory());
            existingRecipe.setType(updatedRecipe.getType());
            existingRecipe.setIngredients(updatedRecipe.getIngredients());
            existingRecipe.setSteps(updatedRecipe.getSteps());
            existingRecipe.setAuthor(updatedRecipe.getAuthor());
            existingRecipe.setCuisine(updatedRecipe.getCuisine());
            recipeRepository.save(existingRecipe);
            System.out.println(existingRecipe.getRecipeName()+" updated successfully");
            return existingRecipe;
        } else {
            throw new InvalidRecipeException("Invalid Recipe Id!!");
        }
    }
	
	 public Recipe deleteRecipe(String recipeId) throws InvalidRecipeException {
	        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
	        if (optionalRecipe.isPresent()) {
	            recipeRepository.deleteById(recipeId);
	            System.out.println(optionalRecipe.get().getRecipeName()+" deleted successfully");
	            return optionalRecipe.get();
	        } else {
	            throw new InvalidRecipeException("Invalid Recipe Id!!");
	        }
	    }
	
	 public List<Recipe> getAllRecipes() {
	        return recipeRepository.findAll();
	    }
	 
	 public List<Recipe> getRecipeByType(String type) throws InvalidRecipeException {
		 if(type.equalsIgnoreCase("Veg")|| type.equalsIgnoreCase("NonVeg")) {
			 return recipeRepository.findByType(type);
		 }
		 else {
			 throw new InvalidRecipeException("Invalid Type for the recipes");
		 }
		
	 }
	 
	 public List<Recipe> getRecipeByName(String recipeName) {
	        return recipeRepository.findByRecipeName(recipeName);
	    }

	 public List<Recipe> getRecipeByCuisine(String cuisine) {
		 	return recipeRepository.findByCuisine(cuisine);
	 }

	 public List<Recipe> getRecipeByCategory(String category) {
		 	return recipeRepository.findByCategory(category);
	 }

	 public List<Recipe> getRecipeByAuthor(String author) {
			return recipeRepository.findByAuthor(author);
	 }

	public Recipe getRecipeById(String recipeId) throws InvalidRecipeException {
		Optional<Recipe> recipe=recipeRepository.findById(recipeId);
		if(!recipe.isEmpty()) {
			 return recipe.get();
		 }
		 else {
			 throw new InvalidRecipeException("Invalid ID!!!");
		 }
	}

}
