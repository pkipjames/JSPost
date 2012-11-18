package com.tsystems.jschool.web.convert;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("com.tsystems.jschool.web.convert.CalendarConverter")
public class CalendarConverter implements Converter {
	private static final String PATTERN = "dd-MM-yyyy";

	public Object getAsObject(FacesContext arg0, UIComponent component, String value) {
		String[] arr = value.split("-");
		Calendar c = Calendar.getInstance();
		try {
			c.set(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
		} catch (NumberFormatException e) {
			c = null;
		}
		
		return c;
	}

	public String getAsString(FacesContext arg0, UIComponent component, Object value) {
		String pattern = (String) component.getAttributes().get("datePattern");
		if (pattern == null) {
			pattern = PATTERN; 
		}
		Calendar c = (Calendar) value;
		return new SimpleDateFormat(pattern).format(c.getTime());
	}

}
