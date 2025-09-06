package com.example.demo.model;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recipes")
public class Recipe {
	@Id
	String id;
	
	String dishName;
	List<String> ingredients;
	List<String> steps;
	
	
	private byte[] image;
	String imageData;
	
	 public String getImageData() {
	        return imageData;
	    }
	public void setImageData(String imageData) {
        this.imageData = imageData;
    }
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Recipe() {
		super();
	}
	@DBRef
    private User author;
	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getId() {
		return id;
	}
	public Recipe(String dishName, List<String> ingredients, List<String> steps, User author) {
		super();
		this.dishName = dishName;
		this.ingredients = ingredients;
		this.steps = steps;
		this.author=author;
	}
	
	@Override
	public String toString() {
		return "Recipe [id=" + id + ", dishName=" + dishName + ", author=" + author + "]";
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}
	public List<String> getSteps() {
		return steps;
	}
	public void setSteps(List<String> steps) {
		this.steps = steps;
	}
	
	
}
