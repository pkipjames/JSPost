/**
 * 
 */
package com.tsystems.jschool.service;

import java.util.Calendar;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.tsystems.jschool.model.Folder;
import com.tsystems.jschool.model.User;
import com.tsystems.jschool.model.UserRole;
import com.tsystems.jschool.service.FolderService;
import com.tsystems.jschool.service.GenericService;
import com.tsystems.jschool.service.UserService;

/**
 * @author Lilia Abdulina
 * 
 */
public class FolderServiceTest extends BaseServiceTest<Folder> {

	@Autowired
	private FolderService folderService;
	@Autowired
	private UserService userService;
	private User user;
	private UserRole role;

	public FolderServiceTest() {
		super(Folder.class);
	}

	@Override
	protected GenericService<Folder> getService() {
		return folderService;
	}

	@Override
	@Before
	public void testSave() {
		role = userService.getUserRoleByName("ROLE_USER");
		user = new User("User", "To", Calendar.getInstance(), "123", "123",
				"address123", role, "password hint");
		userService.save(user);

		super.testSave();
	}

	@Override
	protected Folder getFirstEntity() {
		return new Folder("folder1", user);
	}

	@Override
	protected Folder getSecondEntity() {
		return new Folder("folder2", user);
	}

}
