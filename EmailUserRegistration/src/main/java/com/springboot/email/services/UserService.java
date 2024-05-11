package com.springboot.email.services;

import org.springframework.stereotype.Service;

import com.springboot.email.domain.User;

@Service
public interface UserService {

	User save(User newuser);
	
	Boolean verifyConfirmKey(String confirmKey);
}
