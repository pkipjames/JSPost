package com.tsystems.jschool.service.impl;

import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tsystems.jschool.model.User;
import com.tsystems.jschool.service.AuthenticationService;
import com.tsystems.jschool.service.UserService;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager; // specific for Spring Security
	private User currentUser;
	@Autowired
	private UserService userService;

	public boolean login(String username, String password) {
		try {
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							username, password));
			if (authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(
						authenticate);
				setCurrentUserByAddress(username);
				return true;
			}
		} catch (AuthenticationException e) {	
			return false;
		}
		return false;
	}

	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		//currentUser.unauthenticate();
	}
	
	private void setCurrentUserByAddress(String username) {
		currentUser = userService.getUserByAddress(username);
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
}
