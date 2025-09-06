package com.jwtauthentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwtauthentication.filter.JwtAuthenticationFilter;
import com.jwtauthentication.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final UserService userService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final CustomLogoutHandler logoutHandler;
	
	


	public SecurityConfig(UserService userService, JwtAuthenticationFilter jwtAuthenticationFilter,
			CustomLogoutHandler logoutHandler) {
		this.userService = userService;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.logoutHandler = logoutHandler;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						req-> req.requestMatchers("/login/**","/register/**","/home")
						.permitAll()
						.requestMatchers("/user/**")
						.hasAnyAuthority("USER")
						.requestMatchers("/admin/**")
						.hasAnyAuthority("ADMIN")
						.anyRequest()
						.authenticated())
				.userDetailsService(userService)
				.sessionManagement(
						session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.logout(l->l.logoutUrl("/logout")
						.addLogoutHandler(logoutHandler)
						.logoutSuccessHandler(
								(request,response, authentication)->SecurityContextHolder.clearContext()))
				.build();			
				
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception {
		return configuration.getAuthenticationManager();
	}
	
}
