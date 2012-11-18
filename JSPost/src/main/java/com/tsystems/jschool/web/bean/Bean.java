package com.tsystems.jschool.web.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.tsystems.jschool.model.User;

public class Bean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String RESOURSE_BUNDLE_PATH = "com.tsystems.jschool.messages.messages";

	private User currentUser;

	protected static void addContextMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle(RESOURSE_BUNDLE_PATH,
				context.getViewRoot().getLocale());

		context.addMessage(null, new FacesMessage(bundle.getString(message)));
	}

	protected static void addCustomContextMessage(String custom, String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle(RESOURSE_BUNDLE_PATH,
				context.getViewRoot().getLocale());

		context.addMessage(null, new FacesMessage(bundle.getString(message)
				.concat(" ").concat(custom)));
	}

	protected static String getParameter(String paramName) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Map<String, String> params = ctx.getExternalContext()
				.getRequestParameterMap();
		return params.get(paramName);
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

}
