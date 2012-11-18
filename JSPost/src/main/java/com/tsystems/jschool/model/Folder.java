package com.tsystems.jschool.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tsystems.jschool.auxiliary.QueryConstant;

@javax.persistence.Entity
@Table(name = "FOLDER")
@NamedQueries({
		@NamedQuery(name = QueryConstant.FOLDER_SELECT_FROM_USER, query = "FROM Folder x WHERE x.user = :user"),
		@NamedQuery(name = QueryConstant.FOLDER_SELECT_BY_NAME_AND_USER, query = "FROM Folder x WHERE x.user = :user AND x.name = :name") })
public class Folder extends Entity {

	private static final long serialVersionUID = 1L;
	@Column(name = "name")
	private String name;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "folder")
	private List<Letter> letters = new ArrayList<Letter>();

	public Folder() {
		super();
	}

	public Folder(String name, User User) {
		super();
		this.name = name;
		this.user = User;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User User) {
		this.user = User;
	}

	public List<Letter> getLetters() {
		return letters;
	}

	public void setLetters(List<Letter> letters) {
		this.letters = letters;
	}

	public void addLetter(Letter letter) {
		letters.add(letter);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((letters == null) ? 0 : letters.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (letters == null) {
			if (other.letters != null)
				return false;
		} else if (!letters.equals(other.letters))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Folder [name=" + name + ", User=" + user + ", letters="
				+ letters + ", toString()=" + super.toString() + "]";
	}

}
