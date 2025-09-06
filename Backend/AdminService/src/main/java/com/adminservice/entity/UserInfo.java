package com.adminservice.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="userInfo")
public class UserInfo {
	
	@Id
	String username;
	@DBRef
	List<Recipe> addedRecipes=new ArrayList<>();
	@DBRef
	List<Recipe> savedRecipes=new ArrayList<>();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Recipe> getAddedRecipes() {
		return addedRecipes;
	}
	public void setAddedRecipes(List<Recipe> addedRecipes) {
		this.addedRecipes = addedRecipes;
	}
	public List<Recipe> getSavedRecipes() {
		return savedRecipes;
	}
	public void setSavedRecipes(List<Recipe> savedRecipes) {
		this.savedRecipes = savedRecipes;
	}
	public UserInfo(String username) {
		super();
		this.username = username;
	        this.addedRecipes = new ArrayList<>(); // Initialize addedRecipes list
	        this.savedRecipes = new ArrayList<>(); // Initialize savedRecipes list
	 
	}
	public UserInfo() {
	}
	
	
	

}
