package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity.csrf().disable()
         .authorizeRequests() 
             .requestMatchers("/","/choice","/welcome","/oneRecipe/**","/recipeList","/oneRecipe","/about","/**")
             .permitAll() // Permit access to all other URLs
             .anyRequest().authenticated()
             .and()
         .formLogin()
         .loginPage("/login") 
         .loginProcessingUrl("/postLogin") 
         .defaultSuccessUrl("/profile") 
         .permitAll(); 
 return httpSecurity.build();
		
	}

}
