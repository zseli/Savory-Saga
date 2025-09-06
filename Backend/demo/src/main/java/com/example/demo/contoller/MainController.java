package com.example.demo.contoller;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.RecipeService;
import com.example.demo.service.UserService;

@Controller
public class MainController {
	
	 @Autowired
	 private RecipeService recipeService;
	 @Autowired
	 private UserService userService;
	 
	 private PasswordEncoder passwordEncoder;
		
	
	@GetMapping("/recipe")
	public String getRecipes(@RequestParam("ingredient1") String ingredient1,
            @RequestParam("ingredient2") String ingredient2,
            Model model) {
		
		System.out.println("Recipe page");
		List<Recipe> recipes = recipeService.findByIngredients(ingredient1, ingredient2);
		for (Recipe recipe : recipes) {
			String base64Image = Base64.encodeBase64String(recipe.getImage());
			recipe.setImageData(base64Image);
        }
		model.addAttribute("recipes", recipes);
		return "recipelist";
	}
	
	
	@GetMapping("/recipeee")
	public String getRecipesss(@RequestParam("ingredient1") String ingredient1,
            @RequestParam("ingredient2") String ingredient2,
            Model model) {
		System.out.println("Recipe page");
		List<Recipe> recipes = recipeService.findByIngredients(ingredient1, ingredient2);
		model.addAttribute("recipes", recipes);
		return "recipelist";
	}
	
	
	
	@GetMapping("/welcome")
	public String showWelcome(Model model) {
		System.out.println("WElcome page");
		return "welcome";
	}
	@GetMapping("/profile")
	public String showProfile(Model model) {
		if(userService.getCurrentUser()!=null) {
			model.addAttribute("user",userService.getCurrentUser());
			System.out.println("profile page");
			return "profile";
		}
		return "login";
		
	}
	@GetMapping("/login")
	public String showLogin(Model model) {
		System.out.println("login page");
		return "login";
	}
	
	@GetMapping("/about")
	public String showAbout(Model model) {
		System.out.println("login page");
		return "about";
	}
	
	@GetMapping("/signup")
	public String showSignup(Model model) {
		System.out.println("signup page");
		return "signup";
	}
	@GetMapping("/choice")
	public String showForm(Model model) {
		System.out.println("Second Page");
		User user=userService.getCurrentUser();
		if(user==null) {
		 model.addAttribute("loginRequired", "yes");
			System.out.println("User is not logged in");
			return "choice";
		}
		model.addAttribute("loginRequired", "no");
		System.out.println("User is logged in");
		return "choice";
	}
	
	@GetMapping("/")
	public String showIndex(Model model) {
		System.out.println("Index page");
		return "index";
	}
	
	@GetMapping("/logout")
	public String doLogout(Model model) {
		userService.setCurrentUser(null);
		return "choice";
	}
	
	@GetMapping("/postRecipe")
	public String showHey(Model model) {
		System.out.println("Index page");
		if(userService.getCurrentUser()!=null) {
		return "addRecipe";
		}else 
			return "login";
	}
	
	@GetMapping("/onerecipe/{recipeId}")
	public String showRecipe(@PathVariable("recipeId") String recipeId,Model model) {
		System.out.println("One recipe page of "+ recipeId);
		Recipe rep=recipeService.findById(recipeId).orElse(null);
		System.out.println( rep);
		model.addAttribute("recipe", rep);
		return "onerecipe";
	}
	
	@PostMapping("/addRecipe")
    public String addRecipe(
            @RequestParam("dishName") String dishName,
            @RequestParam("ingredients[]") List<String> ingredients,
            @RequestParam("steps[]") List<String> steps,
            Model model
    ) {
		if(userService.getCurrentUser()!=null) {
			User user=userService.getCurrentUser();
			 Recipe recipe = new Recipe(dishName, ingredients, steps, user);
		        recipeService.addRecipe(recipe,user);		        
		        System.out.println("Recipe added successfully!"+recipe+ user);
		        model.addAttribute("user",user);
		        return "profile";
		}
		return "login";
       
    }
	@PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file,
    		@RequestParam("dishName") String dishName, 
    		@RequestParam("ingredients[]")  List<String> ingredients,
    		@RequestParam("steps[]") List<String> steps,
    		Model model) {
		if(userService.getCurrentUser()!=null) {
			User user=userService.getCurrentUser();
			Recipe recipe = new Recipe(dishName, ingredients, steps, user);
			recipeService.saveRecipeWithImage(recipe,user, file);	        
		    System.out.println("Recipe added successfully! "+recipe);
		    model.addAttribute("user",user);
		    return "profile";
		}
		return "login";
    }
	
	@GetMapping("/admin")
    public String adminPage(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        System.out.println(userList);
        return "admin";
    }
	
	@PostMapping("/postlogin")
    public String checkLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model
    ) {
		if(userService.validLogin(email, password)) {
			model.addAttribute("user",userService.getCurrentUser());
			return "profile";
		}
		 model.addAttribute("error", "Incorrect username or password");
        return "login";
    }

    @GetMapping("/deleteRecipe/{recipeId}")
    public String deleteRecipe(@PathVariable("recipeId") String recipeId, Model model) {
        recipeService.deleteRecipe(recipeId, userService.getCurrentUser());
        System.out.println("Deletion succesfful");
        model.addAttribute("user",userService.getCurrentUser());
        return "redirect:/profile";
 
    }
	@PostMapping("/addUser")
    public String addUser(
            @RequestParam("username")String username,
            @RequestParam("email")String email,
            @RequestParam("password")String password,
            @RequestParam("role")String role,
            Model model
    ) {
        User user = new User(username, email, password, role);
        userService.addUser(user);
        System.out.println("user added successfully!+ "+user + " "+role);
        return "welcome";
    }
	
	@PostMapping("/register")
	public ResponseEntity registerUser( @RequestParam("username")String username,
            @RequestParam("email")String email,
            @RequestParam("password")String password,
            @RequestParam("role")String role,
            Model model) {
		try {
			if(userService.findById(email).isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exist");	
				}
			User user = new User(username, email, password,role);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userService.addUser(user);
			return ResponseEntity.ok(HttpStatus.CREATED);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
		
	}
	@PostMapping("/validateLogin")
	public ResponseEntity loginUser(@RequestParam("email") String email,
	                                @RequestParam("password") String password) {
	    try {
	        Optional<User> optionalUser = userService.findById(email);
	        if (optionalUser.isPresent()) {
	            User user = optionalUser.get();
	            if (passwordEncoder.matches(password, user.getPassword())) {
	                // Passwords match, successful login
	                return ResponseEntity.ok().body("Login successful");
	            } else {
	                // Passwords don't match, return unauthorized status
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
	            }
	        } else {
	            // User not found, return not found status
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	    } catch (Exception e) {
	        // Internal server error occurred
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	    }
	}

	
}
