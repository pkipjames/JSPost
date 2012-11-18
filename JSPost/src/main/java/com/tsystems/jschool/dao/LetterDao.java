/**
 * 
 */
package com.tsystems.jschool.dao;

import java.util.List;

import com.tsystems.jschool.model.Letter;
import com.tsystems.jschool.model.User;

/**
 * 
 * 
 * @author Lilia Abdulina
 * 
 */
public interface LetterDao extends GenericDao<Letter> {	
	/**
	 * Get letters of the specified user and specified type (inbox, outbox,
	 * etc.)
	 * 
	 * @param user
	 *            user
	 * @return List of the letters or empty list if count of the letters = 0
	 * 
	 * 
	 */
	List<Letter> getUserLetters(User user, String type)
			throws IllegalArgumentException;
}
