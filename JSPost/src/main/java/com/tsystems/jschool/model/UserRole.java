package com.tsystems.jschool.model;

import javax.persistence.Table;

@javax.persistence.Entity
@Table(name="USER_ROLE")
public class UserRole extends Entity {

	private static final long serialVersionUID = 1L;
	private String name;
	
	public UserRole() {}
	
	public UserRole(String roleName) {
		this.name = roleName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
