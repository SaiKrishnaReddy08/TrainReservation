package com.trainReservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainReservation.entity.User;
import com.trainReservation.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	public UserRepository userRepo;
	
	// Registration of New User
	public void registerNewUser(User user) {
		userRepo.save(user);
	}

	// Login
	public boolean authenticate(String userId, String password) {
		List<User> user = userRepo.findByUserIdAndPassword(userId, password);
		if (user.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isPhoneRegistered(String phoneNumber) {
		List<User> user = userRepo.findByPhoneNumber(phoneNumber);
		return user.size()==1;
	}

	public boolean isUserIdTaken(String userId) {
		Optional<User> user = userRepo.findById(userId);
		return user.isPresent();
	}
}
