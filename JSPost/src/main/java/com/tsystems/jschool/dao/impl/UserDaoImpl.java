/**
 * 
 */
package com.tsystems.jschool.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import com.tsystems.jschool.auxiliary.QueryConstant;
import com.tsystems.jschool.dao.UserDao;
import com.tsystems.jschool.model.User;
import com.tsystems.jschool.model.UserRole;

/**
 * @author Lilia Abdulina
 * 
 */
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
	ShaPasswordEncoder passwordEncoder;

	public UserDaoImpl() {
		super(User.class);
		passwordEncoder = new ShaPasswordEncoder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsystems.jschool.dao.UserDao#getUserByPhone(java.lang.String)
	 */
	@Override
	public User getUserByPhone(String phone) {
		User user;
		try {
			Query q = getEntityManager().createNamedQuery(
					QueryConstant.USER_GET_BY_PHONE);
			q.setParameter("phone", phone);
			user = (User) q.getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsystems.jschool.dao.UserDao#getUserByAddress(java.lang.String)
	 */
	@Override
	public User getUserByAddress(String address) {
		User user;
		try {
			Query q = getEntityManager().createNamedQuery(
					QueryConstant.USER_GET_BY_ADDRESS);
			q.setParameter("address", address);
			user = (User) q.getSingleResult();
		} catch (NoResultException e) {
			user = null;
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tsystems.jschool.dao.UserDao#checkUserBeforeRegister(com.tsystems
	 * .jschool.entity.User)
	 */
	@Override
	public User checkUserBeforeRegister(User user) {
		Query query = getEntityManager()
				.createNamedQuery(QueryConstant.USER_GET_BY_ADDRESS_OR_PHONE)
				.setParameter("phone", user.getPhone())
				.setParameter("address", user.getAddress());
		return super.getSingleResultOrNull(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsystems.jschool.dao.UserDao#getUserRoleByName(java.lang.String)
	 */
	@Override
	public UserRole getUserRoleByName(String name)
			throws IllegalArgumentException {
		Query query = getEntityManager().createNamedQuery(
				QueryConstant.USER_GET_ROLE).setParameter("name", name);

		try {
			UserRole role = (UserRole) query.getSingleResult();
			return role;
		} catch (NoResultException e) {
			throw new IllegalArgumentException(String.format(
					"User role %s not found", name));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsystems.jschool.dao.UserDao#login(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsystems.jschool.dao.UserDao#logout()
	 */
	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsystems.jschool.dao.UserDao#getCurrentUser()
	 */
	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
