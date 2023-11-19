package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.Repository.IUserRepository;
import com.project.questapp.entities.User;

@Service
public class UserService {
	IUserRepository userRepository;

	public UserService(IUserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}
	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null) ;
	}
	
	public void deleteById(Long userId) {
		
		userRepository.deleteById(userId);
		
	}
	public User updateOneUser(Long userId, User newUser) {
		Optional<User> user=userRepository.findById(userId);
		if(user.isPresent()) {
			
			User founduser=user.get();
			founduser.setUsername(newUser.getUsername());
			founduser.setPassword(newUser.getPassword());
			userRepository.save(founduser);
			return founduser;
		}
		else
			return null;
	}

	

	
}
