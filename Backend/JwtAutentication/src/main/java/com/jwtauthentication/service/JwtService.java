package com.jwtauthentication.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jwtauthentication.dao.TokenDao;
import com.jwtauthentication.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	private final String SECRET_KEY="1d7bc45d9754a879a6b4453733edd59e91c485c2bd869ec78cb15db3a7d14a83";
	private final TokenDao tokenDao;
	
	
	public JwtService(TokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}

	public String generateToken(User user) {
		String token=Jwts 
				.builder()
				.subject(user.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
				.signWith(getSignInKey())
				.compact();
		return token;
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public boolean isValid(String token,UserDetails user) {
		String username=extractUsername(token);
		
		boolean isValidToken=tokenDao.findByToken(token)
									.map(t->!t.isLoggedOut()).orElse(false);
		
		return username.equals(user.getUsername())&& !isTokenExpired(token) && isValidToken;
	}
	
	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token,Function<Claims,T> resolver) {
		Claims claims=extractAllClaims(token);
		return resolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	private SecretKey getSignInKey() {
		byte[] keyBytes=Decoders.BASE64URL.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
