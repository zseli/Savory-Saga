package com.jwtauthentication.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jwtauthentication.entity.User;


@Repository
public interface UserDao extends MongoRepository<User,String> {
	
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
}
