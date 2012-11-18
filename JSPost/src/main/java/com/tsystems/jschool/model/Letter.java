package com.tsystems.jschool.model;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.tsystems.jschool.auxiliary.QueryConstant;


/**
 * @author Lilia Abdulina User's letters
 */
@javax.persistence.Entity
@Table(name="LETTER")
@NamedQueries({
	@NamedQuery(name=QueryConstant.LETTER_GET_INBOX, 
			query="SELECT x FROM Letter x WHERE x.to = :user AND :user NOT MEMBER OF x.whoDeleted ORDER BY x.id DESC"),
	@NamedQuery(name=QueryConstant.LETTER_GET_OUTBOX, 
			query="SELECT x FROM Letter x WHERE x.from = :user AND :user NOT MEMBER OF x.whoDeleted ORDER BY x.id DESC"),
	@NamedQuery(name=QueryConstant.LETTER_GET_TRASH, 
			query="SELECT x FROM Letter x WHERE :user MEMBER OF x.whoDeleted ORDER BY x.id DESC")
})
public class Letter extends Entity {
	
	public enum LetterStatus {
		NEW, READ, DELETED
	}

	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id_from")
	private User from;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id_to")
	private User to;
	@Column(name="create_date")
	private Calendar date = Calendar.getInstance();
	@Column(name="subject")
	private String subject;
	@Column(name="message")
	@Lob
	private String message;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="folder_id")
	private Folder folder;
	@Enumerated(EnumType.ORDINAL)
	@Column(name="status")
	private LetterStatus status = LetterStatus.NEW;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="LETTER_DELETED",
				joinColumns={@JoinColumn(name="letter_id")},
				inverseJoinColumns={@JoinColumn(name="user_id")})
	private Set<User> whoDeleted;

	public Letter() {
		super();
	}
	
	public Letter(User from, User to, String subject,
			String message) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.message = message;
	}
	
	public Letter(User from, User to, Calendar date, String subject,
			String message) {
		super();
		this.from = from;
		this.to = to;
		this.date = date;
		this.subject = subject;
		this.message = message;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public LetterStatus getStatus() {
		return status;
	}

	public void setStatus(LetterStatus status) {
		this.status = status;
	}

	public Set<User> getWhoDeleted() {
		return whoDeleted;
	}

	public void setWhoDeleted(Set<User> whoDeleted) {
		this.whoDeleted = whoDeleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		Letter other = (Letter) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Letter [from=" + from + ", to=" + to + ", date=" + date.getTime()
				+ ", subject=" + subject + ", message=" + message
				+ ", toString()=" + super.toString() + "]";
	}

}
