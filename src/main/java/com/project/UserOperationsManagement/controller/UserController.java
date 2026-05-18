package com.project.UserOperationsManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.UserOperationsManagement.Entity.User;
import com.project.UserOperationsManagement.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {
	
	@Autowired
	private UserService userservice;
		
	@GetMapping("/get-all")
	public List<User> getAllUsers() {
	    return userservice.getAllUsers();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
	    return userservice.getUserById(id);
	}
	
	@PutMapping("/update/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) {
	    return userservice.updateUser(id, user);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id) {
	    userservice.deleteUser(id);
	    return "User deleted successfully";
	}
	

}


