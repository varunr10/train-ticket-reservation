package com.assignment.trainticketreservation.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.assignment.trainticketreservation.entities.User;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

	Map<String, User> emailByUsersMap;

	@PostConstruct
	void init() {
		emailByUsersMap = new HashMap<>();
	}
	
	public void addUser(User user) {
		emailByUsersMap.put(user.getEmail(),user);
	}
	
	public void findUser(String email) {
		emailByUsersMap.get(email);
	}
}
