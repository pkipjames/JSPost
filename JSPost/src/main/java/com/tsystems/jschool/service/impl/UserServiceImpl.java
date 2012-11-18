package com.tsystems.jschool.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.jschool.auxiliary.QueryConstant;
import com.tsystems.jschool.dao.UserDao;
import com.tsystems.jschool.exception.DuplicateException;
import com.tsystems.jschool.model.User;
import com.tsystems.jschool.model.UserRole;
import com.tsystems.jschool.service.UserService;

@Repository("userService")
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User> implements
		UserService, Serializable {
	UserDao userDao;

	public UserServiceImpl(UserDao uDao) {
		super(uDao);
		this.dao = uDao;
		this.userDao = uDao;
	}

	private static final long serialVersionUID = 1L;

	@Transactional(readOnly = true)
	public User getUserByAddress(String address)
			throws IllegalArgumentException {
		if (address.isEmpty()) {
			throw new IllegalArgumentException("Address field can not be null");
		}
		return userDao.getUserByAddress(address);
	}

	@Transactional(readOnly = true)
	public User getUserByPhone(String phone) throws IllegalArgumentException {
		if (phone.isEmpty()) {
			throw new IllegalArgumentException("Phone field can not be null");
		}
		return userDao.getUserByPhone(phone);
	}

	@Override
	public User save(User entity) throws IllegalArgumentException {
		if (entity == null) {
			throw new IllegalArgumentException("User can not be null");
		}
		checkUserBeforeRegister(entity);
		entity.setRole(getUserRoleByName(QueryConstant.ROLE_USER));
		return super.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public void checkUserBeforeRegister(User user)
			throws IllegalArgumentException, DuplicateException {
		if (user == null) {
			throw new IllegalArgumentException("User can not be null");
		}
		User checkUser = userDao.checkUserBeforeRegister(user);
		if (checkUser != null) {
			throw new DuplicateException("This user is already exist");
		}
	}

	@Override
	public UserRole getUserRoleByName(String name)
			throws IllegalArgumentException {
		if (name == null) {
			throw new IllegalArgumentException("User role name can not be null");
		}
		return userDao.getUserRoleByName(name);
	}

	/* (non-Javadoc)
	 * @see com.tsystems.jschool.service.UserService#getPasswordHint(java.lang.String)
	 */
	@Override
	public String getPasswordHint(String address) {
		User user;
		try {
			user = getUserByAddress(address);			
		}catch (IllegalArgumentException e) {
			return null;
		}
		if (user == null) return null;
		return user.getPasswordHint();
	}
}
