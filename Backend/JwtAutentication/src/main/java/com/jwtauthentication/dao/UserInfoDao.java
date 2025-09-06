package com.jwtauthentication.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jwtauthentication.entity.UserInfo;

public interface UserInfoDao  extends MongoRepository<UserInfo,String>{

}
