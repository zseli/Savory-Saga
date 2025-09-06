package com.adminservice.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adminservice.entity.UserInfo;


public interface UserInfoDao  extends MongoRepository<UserInfo,String>{

}
