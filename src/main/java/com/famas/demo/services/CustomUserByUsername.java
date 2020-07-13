package com.famas.demo.services;

 
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.famas.demo.model.Role;
import com.famas.demo.model.User;
import com.famas.demo.util.CustomUserDetails;

@Service
public class CustomUserByUsername {
	
	CustomUserDetails userDetails = new CustomUserDetails();
	private Role role;
	private User user;
	
	public CustomUserByUsername() {
		
	}
	public CustomUserDetails getUser(String username) {
		//call database if the user exists or not if exists return user else return Usernotfoundexception 
		 role = new Role(1,"ROLE_ADMIN");
		 user = new User(1,"utshav","pwd",role);
		 
		userDetails.setUser(user);
		return  userDetails;
		
	}
	
	
}
