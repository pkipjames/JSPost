/**
 * 
 */
package com.tsystems.jschool.service;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.tsystems.jschool.model.Letter;
import com.tsystems.jschool.model.User;
import com.tsystems.jschool.model.UserRole;
import com.tsystems.jschool.service.LetterService;
import com.tsystems.jschool.service.GenericService;
import com.tsystems.jschool.service.UserService;

/**
 * @author Lilia Abdulina
 * 
 */
public class LetterServiceTest extends BaseServiceTest<Letter> {

	@Autowired
	private LetterService letterService;
	@Autowired
	private UserService userService;
	private UserRole role;
	private User userTo;
	private User userFrom;

	public LetterServiceTest() {
		super(Letter.class);
	}

	@Override
	protected GenericService<Letter> getService() {
		return letterService;
	}

	@Override
	@Before
	public void testSave() {
		role = userService.getUserRoleByName("ROLE_USER");
		userTo = new User("User", "To", Calendar.getInstance(), "123", "123",
				"address123", role, "password hint");
		userFrom = new User("User", "From", Calendar.getInstance(), "1234",
				"1234", "address1234", role, "password hint");
		userService.save(userTo);
		userService.save(userFrom);

		super.testSave();
	}

	@Override
	@After
	public void testDelete() {
		super.testDelete();

		if (userTo != null) {
			userService.delete(userTo);
		}
		if (userFrom != null) {
			userService.delete(userFrom);
		}
	}

	@Override
	protected Letter getFirstEntity() {
		return new Letter(userFrom, userTo, "Hello", "message Hello");
	}

	@Override
	protected Letter getSecondEntity() {
		return new Letter(userTo, userFrom, "Bye", "message Bye");
	}

}
