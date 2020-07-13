package com.famas.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.famas.demo.model.JwtRequest;
import com.famas.demo.securityConfig.Authenticationprovider;
import com.famas.demo.util.CustomUserDetails;
import com.famas.demo.util.JwtTokenUtil;

@RestController
public class JwtRequestController {
	 
	@Autowired
	Authenticationprovider authenticationManager;
	@Autowired
	JwtTokenUtil jwtUtil;
	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> userToJwtToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticateUser(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(new String("Your JWT " + jwtUtil.generateToken( userDetails)));
	}

	private void authenticateUser(String username, String password) {
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}
