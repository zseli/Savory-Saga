package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User {
   	private String username;
   	@Id
    private String email;
    private String password;
    private String role;
    
    public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}

	@DBRef
    private List<Recipe> recipes=new ArrayList();
    
    public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public User(String username, String email,String password, String role) {
		super();
		this.username = username;
		this.email = email;
		this.password=password;
		this.role=role;
	}
    
    


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
    
}
