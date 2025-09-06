package com.recipeservice.entity;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="recipe")
public class Recipe {
	
	@Id
	private String recipeId;
	private String recipeName;
	private String category;
	private String type;
	private ArrayList<String> ingredients;
	private String steps;
	private String cuisine;
	private String author;
	
	
	public String getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public Recipe(String recipeId, String recipeName, String category, String type, ArrayList<String> ingredients,
			String steps, String cuisine, String author) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.category = category;
		this.type = type;
		this.ingredients = ingredients;
		this.steps = steps;
		this.cuisine = cuisine;
		this.author = author;
	}
	public Recipe(String recipeId, String recipeName, String category, String type, ArrayList<String> ingredients,
			String steps, String cuisine) {
		super();
		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.category = category;
		this.type = type;
		this.ingredients = ingredients;
		this.steps = steps;
		this.cuisine = cuisine;
	}
	public Recipe() {
	}
	
	
	
	
	
}
