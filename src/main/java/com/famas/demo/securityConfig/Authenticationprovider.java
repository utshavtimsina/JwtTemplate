package com.famas.demo.securityConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.famas.demo.services.CustomUserDetailsService;
import com.famas.demo.util.CustomUserDetails;

@Component
public class Authenticationprovider implements AuthenticationManager {
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		
		CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(authentication.getName());
		 String username = authentication.getName();
	      String password = authentication.getCredentials().toString();
		if(username.equals(user.getUsername()) && password.equals(user.getPassword()) ) {
			 
			 Authentication auth =new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication( auth);
			return auth ;
		}
		throw new BadCredentialsException("Username or password not valid");
	}

}
