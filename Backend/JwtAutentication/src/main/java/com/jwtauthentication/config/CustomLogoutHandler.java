package com.jwtauthentication.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.jwtauthentication.dao.TokenDao;
import com.jwtauthentication.entity.Token;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class CustomLogoutHandler implements LogoutHandler {
	
	private final TokenDao tokenDao;

	public CustomLogoutHandler(TokenDao tokenDao) {
		super();
		this.tokenDao = tokenDao;
	}

	@Override
	public void logout(HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		String authHeader=request.getHeader("Authorization");
		if(authHeader==null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		String token=authHeader.substring(7);
		
		//get stored token from db and mark logout
		Token storedToken=tokenDao.findByToken(token).orElse(null);
		if(token!=null) {
			storedToken.setLoggedOut(true);
			tokenDao.save(storedToken);
		}
		
		
	}

}
