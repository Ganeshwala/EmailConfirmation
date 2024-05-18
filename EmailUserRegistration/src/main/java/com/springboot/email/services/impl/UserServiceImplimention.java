package com.springboot.email.services.impl;

import org.springframework.stereotype.Service;

import com.springboot.email.domain.Confirmation;
import com.springboot.email.domain.User;
import com.springboot.email.repository.ConfirmationRepository;
import com.springboot.email.repository.UserRepository;
import com.springboot.email.services.EmailServices;
import com.springboot.email.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplimention implements UserService{

	private final UserRepository userRepo;
	
	private final ConfirmationRepository confirmationRepo;
	
	private final EmailServices emailService;
	
	@Override
	public User save(User newuser) {
		// TODO Auto-generated method stub
		if(userRepo.existsByEmail(newuser.getEmail())) {
			throw new RuntimeException("User Email already Existed");
		}
		newuser.setActive(false);
		userRepo.save(newuser);
		
		Confirmation newconfirmation = new Confirmation(newuser);
		confirmationRepo.save(newconfirmation);
		
		//sending email 
		//emailService.sendEmailMessage(newuser.getName(), newuser.getEmail(), newconfirmation.getConfirmKey());
		// sending email with attachments
		emailService.sendEmailMessageWithFiles(newuser.getName(), newuser.getEmail(), newconfirmation.getConfirmKey());
		
		emailService.sendHtmlFormatEmail(newuser.getName(), newuser.getEmail(), newconfirmation.getConfirmKey());
		
		return newuser;
	}

	@Override
	public Boolean verifyConfirmKey(String confirmKey) {
		// TODO Auto-generated method stub
		Confirmation newCon = confirmationRepo.findByConfirmKey(confirmKey);
		User user = userRepo.findByEmailIgnoreCase(newCon.getUser().getEmail());
		if(user != null) {
			user.setActive(true);
			userRepo.save(user);
			return true;
		}
			
		return false;
	}

}
