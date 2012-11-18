/**
 * 
 */
package com.tsystems.jschool.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tsystems.jschool.exception.DuplicateException;
import com.tsystems.jschool.model.User;
import com.tsystems.jschool.model.UserRole;
import com.tsystems.jschool.service.GenericService;
import com.tsystems.jschool.service.UserService;

/**
 * @author Lilia Abdulina
 * 
 */
public class UserServiceTest extends BaseServiceTest<User> {

	@Autowired
	private UserService userService;

	private UserRole role;

	public UserServiceTest() {
		super(User.class);
	}

	@Override
	protected GenericService<User> getService() {
		return userService;
	}

	@Override
	protected User getFirstEntity() {
		role = userService.getUserRoleByName("ROLE_USER");
		return new User("first", "user1", Calendar.getInstance(), "12345",
				"12345", "address1", role, "password hint");
	}

	@Override
	protected User getSecondEntity() {
		role = userService.getUserRoleByName("ROLE_USER");
		return new User("second", "user2", Calendar.getInstance(), "123456",
				"123456", "address2", role, "password hint");
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.UserServiceImpl#getUserByAddress(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetUserByAddress() {
		User userByAddress = userService.getUserByAddress("address1");
		assertNotNull(userByAddress);
		assertEquals("first", userByAddress.getFirstName());
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.UserServiceImpl#getUserByAddress(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetUserByAddressReturnNull() {
		User userByAddress = userService.getUserByAddress("failAddress");
		assertNull(userByAddress);
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.UserServiceImpl#getUserByPhone(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetUserByPhone() {
		User userByPhone = userService.getUserByPhone("12345");
		assertNotNull(userByPhone);
		assertEquals("first", userByPhone.getFirstName());
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.UserServiceImpl#getUserByPhone(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetUserByPhoneReturnNull() {
		User userByPhone = userService.getUserByPhone("123456789");
		assertNull(userByPhone);
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.UserServiceImpl#save(com.tsystems.jschool.model.User)}
	 * .
	 */
	@Test(expected = DuplicateException.class)
	public void testSaveUserDupcicate() {
		userService.save(entity1);
	}

	/**
	 * Test method for
	 * {@link com.tsystems.jschool.service.impl.UserServiceImpl#getUserRoleByName(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetUserRoleByName() {
		UserRole userRoleByName = userService.getUserRoleByName("ROLE_USER");
		assertNotNull(userRoleByName);
		assertEquals(entity1.getRole(), userRoleByName);
	}

}
