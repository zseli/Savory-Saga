package com.adminservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminservice.dao.UserDao;
import com.adminservice.dao.UserInfoDao;
import com.adminservice.entity.Roles;
import com.adminservice.entity.User;
import com.adminservice.entity.UserInfo;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final UserInfoDao userInfoDao;
    private final UserDao userDao;

    @Autowired
    public AdminService(UserInfoDao userInfoDao, UserDao userDao) {
        this.userInfoDao = userInfoDao;
        this.userDao =userDao;
    }

    public List<UserInfo> getAllUsers() {
        return userInfoDao.findAll();
    }

	public void changeUserRole(String username) {
		// TODO Auto-generated method stub
		Optional<User> user=userDao.findById(username);
		if(user.isPresent()) {
			User user1=user.get();
			user1.setRole(Roles.ADMIN);
			userDao.save(user1);			
		}
		
	}
}
