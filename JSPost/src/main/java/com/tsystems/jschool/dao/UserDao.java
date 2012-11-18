package com.tsystems.jschool.dao;

import com.tsystems.jschool.model.User;
import com.tsystems.jschool.model.UserRole;
import com.tsystems.jschool.service.AuthenticationService;
import com.tsystems.jschool.service.UserService;

/**
 * UserDao interface provides access to the database for both
 * {@link AuthenticationService} and {@link UserService}.
 * 
 * @author Lilia Abdulina
 */
public interface UserDao extends GenericDao<User> {

	User getUserByPhone(String phone);

	User getUserByAddress(String address);

	User checkUserBeforeRegister(User user);

	UserRole getUserRoleByName(String name);

	boolean login(String username, String password);

	void logout();

	User getCurrentUser();
}
