package com.trainReservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainReservation.entity.User;


public interface UserRepository extends JpaRepository<User, String> {
	
	List<User> findByUserIdAndPassword(String userId, String password);

	List<User> findByPhoneNumber(String phoneNumber);
}
