package com.jwtauthentication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jwtauthentication.entity.Token;


public interface TokenDao extends MongoRepository<Token,String> {	
	
	List<Token> findAllByUserUsernameAndLoggedOutFalse(String username);
	Optional<Token> findByToken(String token);

}
