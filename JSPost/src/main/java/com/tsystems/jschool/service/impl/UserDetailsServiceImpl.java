package com.tsystems.jschool.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.jschool.model.User;

/*
 * Spring-security requires an implementation of UserDetailService. 
 */
@SuppressWarnings("deprecation")
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	@PersistenceContext(unitName = "ApplicationEntityManager")
	private EntityManager em;

	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		org.springframework.security.core.userdetails.User user = null;
		try {

			User u = (User) em
					.createQuery("select u from User u where u.address = :login")
					.setParameter("login", username).getSingleResult();

			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;

			Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new GrantedAuthorityImpl(u.getRole()
					.getName())); // RoleName = "ROLE_ADMIN" or
									// "ROLE_REGISTERED"

			user = new org.springframework.security.core.userdetails.User(u
					.getAddress().toString(), u.getPassword(), enabled,
					accountNonExpired, credentialsNonExpired, accountNonLocked,
					grantedAuthorities);

		} catch (NoResultException e) {
			throw new UsernameNotFoundException("No such user with specified data");
		}
		return user;
	}

}
