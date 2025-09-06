package com.jwtauthentication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtauthentication.dao.TokenDao;
import com.jwtauthentication.dao.UserDao;
import com.jwtauthentication.dao.UserInfoDao;
import com.jwtauthentication.entity.AuthenticationResponse;
import com.jwtauthentication.entity.Token;
import com.jwtauthentication.entity.User;
import com.jwtauthentication.entity.UserInfo;

@Service
public class AuthenticationService {
	private final UserDao userDao;
	private final UserInfoDao userInfoDao;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final TokenDao tokenDao;	
	private AuthenticationResponse currentUser;
	
	public AuthenticationResponse getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(AuthenticationResponse currentUser) {
		this.currentUser = currentUser;
	}




		public AuthenticationService(UserDao userDao, UserInfoDao userInfoDao, PasswordEncoder passwordEncoder,
			JwtService jwtService, AuthenticationManager authenticationManager, TokenDao tokenDao) {
		super();
		this.userDao = userDao;
		this.userInfoDao = userInfoDao;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.tokenDao = tokenDao;
	}

	//adding new user
	public AuthenticationResponse register(User request) {
		 if (userDao.existsByUsername(request.getUsername())) {
		        throw new RuntimeException("Username already exists");
		    }
		User user=new User();
		user.setEmail(request.getEmail());
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		user.setRole(request.getRole());
		user=userDao.save(user);
		
		String token=jwtService.generateToken(user);
		UserInfo userInfo=new UserInfo();
		userInfo.setUsername(request.getUsername());
		userInfoDao.save(userInfo);
		saveUserToken(user, token);
		currentUser= new AuthenticationResponse(token,user.getUsername());
		return currentUser;
		
	}


	private void saveUserToken(User user, String token) {
		Token token1=new Token();
		token1.setToken(token);
		token1.setLoggedOut(false);
		token1.setUser(user);
		tokenDao.save(token1);
	}
	
	//authenticating login
	public AuthenticationResponse authenticate(User request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
				);
		User user=userDao.findByUsername(request.getUsername()).orElseThrow();
		String token=jwtService.generateToken(user);
		
		revokeAllTokensByUser(user);		
		saveUserToken(user, token);
		currentUser= new AuthenticationResponse(token,user.getUsername());
		return currentUser;
	}


	private void revokeAllTokensByUser(User user) {
		List<Token> validTokenList=tokenDao.findAllByUserUsernameAndLoggedOutFalse(user.getUsername());
		if(!validTokenList.isEmpty()) {
			validTokenList.forEach(t->{
				t.setLoggedOut(true);
			});
		}
		this.setCurrentUser(null);
		tokenDao.saveAll(validTokenList);
	}

}
