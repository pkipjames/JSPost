package com.tsystems.jschool.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tsystems.jschool.model.User;

public class UserDaoTest extends BaseDaoTestCase {

	@Autowired
	private UserDao dao;

	@Test
	public void testGetUser() throws Exception {
		User user = dao.get(77);
		assertNotNull(user);
	}

	@Test
	public void testUserExists() throws Exception {
		assertTrue(dao.exists(77));
	}

	@Test
	public void testUserNotExists() throws Exception {
		assertFalse(dao.exists(1111));
	}

	@Test(expected = ConstraintViolationException.class)
	public void testAddAndRemoveUser() throws Exception {
		User user = new User();
		String address = "testAddRem";
		user.setFirstName("John");
		user.setLastName("Elway");
		user.setAddress(address);
		user = dao.save(user);
		user = dao.get(user.getId());

		assertEquals("John", user.getFirstName());
		assertNotNull(user.getId());

		dao.remove(user);

		// should throw EntityNotFoundException
		dao.remove(user);
	}
}
