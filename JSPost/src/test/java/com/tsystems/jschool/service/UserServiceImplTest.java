/**
 * 
 */
package com.tsystems.jschool.service;

import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import com.tsystems.jschool.dao.UserDao;
import com.tsystems.jschool.model.User;
import com.tsystems.jschool.service.impl.UserServiceImpl;

/**
 * @author Lilia Abdulina
 * 
 */
public class UserServiceImplTest extends TestCase {

	private final Logger log = Logger.getLogger(UserServiceImplTest.class);
	private UserServiceImpl manager = null;
	private UserDao dao = null;
	private User user = null;

	protected void setUp() throws Exception {
		log.debug("setUpDao for UserManagerImplTest");
		dao = createStrictMock(UserDao.class);
		manager = new UserServiceImpl(dao);
	}

	public void testGetUser() {
		log.debug("testing get user...");

		Integer id = 7;
		user = new User();

		// set expected behavior on dao
		expect(dao.get(id)).andReturn(user);
		replay(dao);

		User result = manager.getEntityById(id);
		assertSame(user, result);
		verify(dao);
	}

	public void testGetUsers() {
		log.debug("testing getAll...");

		List<User> users = new ArrayList<User>();

		// set expected behavior on dao
		expect(dao.getAll()).andReturn(users);
		replay(dao);

		List<User> result = manager.getAllEntities();
		assertSame(users, result);
		verify(dao);
	}

	/*public void testSaveUser() {
		log.debug("testing save user...");

		user = new User();
		user.setId(5);
		user.setFirstName("Matt");
		user.setLastName("Raible");
		user.setPhone("43444444");
		user.setPassword("123");
		user.setAddress("123");
		user.setBirthDate(Calendar.getInstance());

		// set expected behavior on dao
		expect(dao.save(user)).andReturn(user);
		replay(dao);

		manager.save(user);
		verify(dao);
	}*/

	/*public void testRemoveUser() {
		log.debug("testing remove user...");

		final Long id = -11L;

		// set expected behavior on dao
		dao.remove(id);
		replay(dao);

		manager.remove(id);
		verify(dao);
	}*/
}