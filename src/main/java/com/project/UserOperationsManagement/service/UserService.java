package com.project.UserOperationsManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.UserOperationsManagement.Entity.User;
import com.project.UserOperationsManagement.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userrepository;       

	public List<User> getAllUsers() {
	    return userrepository.findAll();
	}
	
	public User getUserById(int id) {
	    return userrepository.findById(id)
	    		.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	}
	
	public User updateUser(int id, User updatedUser) {

	    User existingUser = userrepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

	    // Update fields
	    existingUser.setUserName(updatedUser.getUserName());
	    existingUser.setPassword(updatedUser.getPassword());
	    existingUser.setEmail(updatedUser.getEmail());
	    existingUser.setName(updatedUser.getName());
	    existingUser.setAge(updatedUser.getAge());

	    return userrepository.save(existingUser);
	}
	
	public void deleteUser(int id) {

	    User user = userrepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

	    userrepository.delete(user);
	}
	
	
	

	
}




