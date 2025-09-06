package com.recipeservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.recipeservice.entity.Recipe;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe,String> {
	
	Optional<Recipe> findById(String recipeId);	
	List<Recipe> findByRecipeName(String recipeName);
	List<Recipe> findByAuthor(String author);
	List<Recipe> findByType(String type);
	List<Recipe> findByCategory(String category);
	List<Recipe> findByCuisine(String cuisine);
	
	@Query("{ 'ingredients' : { $regex: ?0, $options: 'i' }, 'ingredients' : { $regex: ?1, $options: 'i' } }")
	List<Recipe> findByIngredients(String in1, String in2);
	

}
