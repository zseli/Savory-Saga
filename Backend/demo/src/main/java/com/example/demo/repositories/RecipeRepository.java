package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.model.Recipe;

public interface RecipeRepository extends MongoRepository<Recipe, String>{
	
	//@Query("{ 'ingredients' : { $in : [ ?0, ?1 ] } }")
	@Query("{ 'ingredients' : { $regex: ?0, $options: 'i' }, 'ingredients' : { $regex: ?1, $options: 'i' } }")
	List<Recipe> findByIngredients(String in1, String in2);
	
	
	Optional<Recipe> findById(String id);
}
