package com.jwtauthentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwtauthentication.dao.UserDao;
import com.jwtauthentication.entity.User;

@Service
public class UserService implements UserDetailsService{
	
	private final UserDao userDao;
	
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
	
	public User registerNewUser(User user) {
		return userDao.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User not found!!"));
	}

}
