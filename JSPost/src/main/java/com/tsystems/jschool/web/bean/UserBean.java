package com.tsystems.jschool.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.tsystems.jschool.exception.DuplicateException;
import com.tsystems.jschool.model.User;
import com.tsystems.jschool.service.AuthenticationService;
import com.tsystems.jschool.service.UserService;

@ManagedBean(name = "user")
@SessionScoped
public class UserBean extends Bean {

	private static final long serialVersionUID = 1L;
	private User user = new User();

	@ManagedProperty("#{userService}")
	private UserService userService;
	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService;

	public String doLogin() {
		boolean success = authenticationService.login(user.getAddress(),
				user.getPassword());

		if (success) {
			user = authenticationService.getCurrentUser();
			setCurrentUser(user);
			return "/pages/index/letters.xhtml?faces-redirect=true";
		} else {
			addContextMessage("login_err");
			return "/authFail.xhtml";
		}
	}

	public String doLogout() {
		authenticationService.logout();

		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}

	public String doRegister() {
		try {
			userService.save(user);
			return doLogin();
		} catch (DuplicateException e) {
			addContextMessage("registration_is_user");
			return "/registerFail.xhtml";
		}

	}

	public String getPasswordHint() {
		String hint = userService.getPasswordHint(user.getAddress());

		boolean success = ((hint != null) ? true : false);
		if (success) {
			addCustomContextMessage(hint, "pass_hint_msg");
		} else
			addContextMessage("login_err");
		return null;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(
			AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

}
