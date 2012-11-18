package com.tsystems.jschool.web.util;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LocaleConfig implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale() {
        return locale;
    }
	
	public String changeLocale() {
		FacesContext context = FacesContext.getCurrentInstance();
		locale = new Locale(getLanguage(context));
		context.getViewRoot().setLocale(locale);
		return null;
	}
	
	private String getLanguage(FacesContext ctx) {
		Map<String, String> params = ctx.getExternalContext().getRequestParameterMap();
		return params.get("language");
	}
}
