package com.tsystems.jschool.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.tsystems.jschool.auxiliary.QueryConstant;

/**
 * The <code>User</code> class represents information about registered users of
 * JSPost. It contains first name ({@code fName}), second name ({@code sName}),
 * phone number ({@code phone}) and birth date ({@code bDate}) of user and
 * returns them on request.
 * 
 * The <code>User</code> class also contains {@code login} field which serves as
 * UID. In addition, it will be the name of e-mail address for this user. Two
 * users can not own identical logins.
 * 
 * @author Lilia Abdulina
 * 
 */
@javax.persistence.Entity
@Table(name = "USER")
@NamedQueries({
		@NamedQuery(name = QueryConstant.USER_LOGIN, query = "SELECT x FROM User x WHERE x.phone = :phone AND x.password = :password"),
		@NamedQuery(name = QueryConstant.USER_GET_BY_ADDRESS, query = "SELECT x FROM User x WHERE x.address = :address"),
		@NamedQuery(name = QueryConstant.USER_GET_BY_PHONE, query = "SELECT x FROM User x WHERE x.phone = :phone"),
		@NamedQuery(name = QueryConstant.USER_GET_BY_ADDRESS_OR_PHONE, query = "SELECT x FROM User x WHERE x.phone = :phone OR x.address = :address"),
		@NamedQuery(name = QueryConstant.USER_GET_ROLE, query = "SELECT x FROM UserRole x WHERE x.name = :name") })
public class User extends Entity {

	private static final long serialVersionUID = 1L;

	/** The first name of the user (name) */
	@Column(name = "first_name")
	@NotNull
	private String firstName;

	/** The second name of the user (surname) */
	@Column(name = "last_name")
	@NotNull
	private String lastName;

	/** The birth date of the user */
	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Calendar birthDate;

	/** The phone number of the user */
	@Column(name = "phone", unique = true)
	private String phone;

	/** The users security password */
	@Column(name = "password")
	@NotNull
	private String password;

	/** The role of the user. Needed for authorization and security */
	@ManyToOne
	@JoinColumn(name = "role_id")
	private UserRole role;

	/** The login and e-mail address of the user */
	@Column(name = "address", unique = true)
	@NotNull
	private String address;

	@Column(name = "passwordHint")
	@NotNull
	private String passwordHint;

	public User() {
		super();
	}

	public User(String firstName, String lastName, Calendar birthtDate,
			String phone, String password, String address, UserRole role,
			String passwordHint) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthtDate;
		this.phone = phone;
		this.password = password;
		this.address = address;
		this.role = role;
		this.passwordHint = passwordHint;
	}

	public User(String address, String password) {
		super();
		this.address = address;
		this.password = password;
	}
	
	public User(String pHint){
		super();
		this.passwordHint = pHint;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstNane) {
		this.firstName = firstNane;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthtDate) {
		this.birthDate = birthtDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User) o;

		if (firstName != null ? !firstName.equals(user.firstName)
				: user.firstName != null)
			return false;
		if (lastName != null ? !lastName.equals(user.lastName)
				: user.lastName != null)
			return false;
		if (address != null ? !address.equals(user.address)
				: user.address != null)
			return false;
		if (phone != null ? !phone.equals(user.phone) : user.phone != null)
			return false;

		return true;
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this,
				ToStringStyle.DEFAULT_STYLE).append("id: ", this.getId())
				.append(" login: ", this.address)
				.append(" first name: ", this.firstName)
				.append(" second name: ", this.lastName)
				.append(" phone number: ", this.phone)
				.append(" birth date: ", this.birthDate.getTime());

		return sb.toString();
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the passwordHint
	 */
	public String getPasswordHint() {
		return passwordHint;
	}

	/**
	 * @param passwordHint
	 *            the passwordHint to set
	 */
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

}
