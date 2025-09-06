package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repositories.RecipeRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class RecipeService implements RecipeRepository{
	
	
	@Autowired
    private RecipeRepository repository;
	@Autowired
	private UserService userService;
	
	@Override
	public List<Recipe> findByIngredients(String in1, String in2) {
		// TODO Auto-generated method stub
		List<Recipe> list= repository.findByIngredients(in1, in2);
		System.out.println(list);
		return list;
	}
	
    public void addRecipe(Recipe recipe, User user) {
        repository.save(recipe);
        List<Recipe> list=user.getRecipes();
        list.add(recipe);
        user.setRecipes(list);
        userService.addUser(user);
    }
    
    public void saveRecipeWithImage(Recipe recipe,User user, MultipartFile file) {
        try {
            recipe.setImage(file.getBytes());
            repository.save(recipe);
            List<Recipe> list=user.getRecipes();
            list.add(recipe);
            user.setRecipes(list);
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
	
    public void deleteRecipe(String recipeId, User user) {
    		List<Recipe> list=user.getRecipes();
    		Recipe recipe=null;
    		for(Recipe rep:list) {
    			if(rep.getId().equals(recipeId)) {
    				recipe=rep;
    			}
    		} 

			list.remove(recipe);
			repository.delete(recipe);
			user.setRecipes(list);
			userService.addUser(user);
			System.out.println("Deleted successfully"+recipe.getId());
    	}
    
    
    public byte[] getImageData(String recipeId) {
        Recipe recipe = repository.findById(recipeId).orElse(null);
        if (recipe != null) {
            return recipe.getImage();
        } else {
            return null;
        }
    }
    
    
    
    
    
    
    
    
	@Override
	public <S extends Recipe> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Recipe> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Recipe> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Recipe> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Recipe> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recipe> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recipe> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Recipe> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Recipe> findById(String id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Recipe entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Recipe> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Recipe> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Recipe> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Recipe> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends Recipe> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Recipe> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Recipe> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Recipe, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
