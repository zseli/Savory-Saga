package com.userservice.entities;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDAO  extends MongoRepository<UserInfo,String>{
}
