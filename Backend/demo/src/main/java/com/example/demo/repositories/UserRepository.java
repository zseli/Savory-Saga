package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.User;

public interface UserRepository extends MongoRepository<User, String> {
}
