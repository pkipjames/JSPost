package com.tsystems.jschool.service;

import com.tsystems.jschool.model.User;

public interface AuthenticationService {

	boolean login(String username, String password);

	void logout();

	User getCurrentUser();
}
