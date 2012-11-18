package com.tsystems.jschool.service;

import java.util.List;

import com.tsystems.jschool.model.Letter;
import com.tsystems.jschool.model.User;

/**
 * GenericService interface for actions with User's letters
 * 
 * @author Lilia Abdulina
 */
public interface LetterService extends GenericService<Letter> {

	/**
	 * Get all letter Current user
	 * 
	 * @param user
	 *            current user
	 * @return List of letter, or empty list if count items = 0
	 */
	List<Letter> getUserLetters(User user, String type)
			throws IllegalArgumentException;

}
