package com.famas.demo.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	CustomUserByUsername user;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Logger log = LoggerFactory.getLogger(this.getClass());
		UserDetails userExists = user.getUser(username);
		if( userExists!= null) {
			return userExists ;
		}
		throw new UsernameNotFoundException("Unautorized");
	}

}
