package com.userservice.service;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.userservice.dto.AuthenticationResponse;
import com.userservice.dto.Recipe;
import com.userservice.entities.UserInfo;
import com.userservice.entities.UserInfoDAO;
import com.userservice.exception.InvalidRecipeException;
import com.userservice.exception.InvalidUserException;
import com.userservice.proxy.RecipeProxy;

@Service
public class UserServiceImp {
	
	private final UserInfoDAO userInfodao;
	
	
	public UserServiceImp(UserInfoDAO userInfodao) {
		super();
		this.userInfodao = userInfodao;
	}

	@Autowired
	private RecipeProxy repProxy;
	
	public String greeting(String username) {
		return "Welcome to User Service "+username;
	}

	public Optional<UserInfo> findByUsername(String username) throws InvalidUserException{
		// TODO Auto-generated method stub
		Optional<UserInfo> list= userInfodao.findById(username);
		if(list.isEmpty()) {
			throw new InvalidUserException("User does not exist");
		}
		return list;
	}
	
	public String saveRecipe(Recipe recipe, String username) throws InvalidUserException {
		Optional<UserInfo> user= userInfodao.findById(username);
		if(!user.isEmpty()) {
			UserInfo currentUser=user.get();
			List<Recipe> list =currentUser.getSavedRecipes();
			System.out.println(list);
			list.add(recipe);
			System.out.println(list);
			currentUser.setSavedRecipes(list);
			userInfodao.save(currentUser);
			System.out.println(currentUser);
			return recipe.getRecipeName()+" saved successfully for "+ currentUser.getUsername();	
		}
		else {
			throw new InvalidUserException("User does not exist");
		}
		
	}

	public UserInfo seeProfile(String username) {
		UserInfo user=userInfodao.findById(username).orElse(null);
		System.out.println(user);
		return user;
	}

	public Recipe addRecipe(Recipe addedRecipe) throws InvalidUserException {
		// TODO Auto-generated method stub
		Optional<UserInfo> user= userInfodao.findById(addedRecipe.getAuthor());
		if(!user.isEmpty()) {
			UserInfo currentUser=user.get();
			List<Recipe> list =currentUser.getAddedRecipes();
			System.out.println(list);
			list.add(addedRecipe);
			System.out.println(list);
			currentUser.setAddedRecipes(list);
			userInfodao.save(currentUser);
			System.out.println(currentUser);
			return addedRecipe;	
		}
		else {
			throw new InvalidUserException("User does not exist");
		}
		
		
	}


	public void deleteRecipe(String username, Recipe recipe) throws InvalidRecipeException{
		Optional<UserInfo> userInfo=userInfodao.findById(username);
		if(userInfo.isPresent()) {
			UserInfo user=userInfo.get();
			List<Recipe> list=user.getAddedRecipes();
			if(list.contains(recipe)) {
				list.remove(recipe);
			}
			user.setAddedRecipes(list);
			userInfodao.save(user);
		}
		
	}

	public List<UserInfo> findAllUsers() {
		// TODO Auto-generated method stub
		return userInfodao.findAll();
	}
	
}
