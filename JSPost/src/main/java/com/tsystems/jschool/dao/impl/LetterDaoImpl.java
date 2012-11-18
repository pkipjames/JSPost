/**
 * 
 */
package com.tsystems.jschool.dao.impl;

import static com.tsystems.jschool.auxiliary.MessageConstant.INBOX;
import static com.tsystems.jschool.auxiliary.MessageConstant.OUTBOX;
import static com.tsystems.jschool.auxiliary.MessageConstant.TRASH;

import java.util.List;

import javax.persistence.Query;

import com.tsystems.jschool.auxiliary.QueryConstant;
import com.tsystems.jschool.dao.LetterDao;
import com.tsystems.jschool.model.Letter;
import com.tsystems.jschool.model.User;

/**
 * @author Lilia Abdulina
 * 
 */
public class LetterDaoImpl extends GenericDaoImpl<Letter> implements LetterDao {

	public LetterDaoImpl() {
		super(Letter.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tsystems.jschool.dao.LetterDao#getUserLetters(com.tsystems.jschool
	 * .entity.User, java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public List<Letter> getUserLetters(User user, String type)
			throws IllegalArgumentException {
		Query q = null;
		if (type.equals(INBOX)) {
			q = getEntityManager().createNamedQuery(
					QueryConstant.LETTER_GET_INBOX);
		} else if (type.equals(OUTBOX)) {
			q = getEntityManager().createNamedQuery(
					QueryConstant.LETTER_GET_OUTBOX);
		} else if (type.equals(TRASH)) {
			q = getEntityManager().createNamedQuery(
					QueryConstant.LETTER_GET_TRASH);
		}
		q.setParameter("user", user);
		return q.getResultList();
	}

}
