package com.tsystems.jschool.service;

import com.tsystems.jschool.model.User;
import com.tsystems.jschool.model.UserRole;

/**
 * GenericService interface for actions with Users
 * 
 * @author Lilia Abdulina 
 * 
 */
public interface UserService extends GenericService<User> {

	/**
	 * Get user by phone number
	 * 
	 * @param phone
	 *            user phone
	 * @return User, if object found, or null, if user not found
	 */
	User getUserByPhone(String phone);

	User getUserByAddress(String address);

	void checkUserBeforeRegister(User user);

	UserRole getUserRoleByName(String name);

	/**
	 * @param address
	 * @return password hint for the user specified by his address
	 */
	String getPasswordHint(String address);
}
