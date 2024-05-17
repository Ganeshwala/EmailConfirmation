package com.springboot.email.services;

import com.springboot.email.domain.User;


public interface UserService {

	User save(User newuser);
	
	Boolean verifyConfirmKey(String confirmKey);
}
