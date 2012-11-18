package com.tsystems.jschool.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.jschool.dao.LetterDao;
import com.tsystems.jschool.model.Letter;
import com.tsystems.jschool.model.User;
import com.tsystems.jschool.service.LetterService;

@Repository("letterService")
@Transactional
public class LetterServiceImpl extends GenericServiceImpl<Letter> implements LetterService {
	
	private static final long serialVersionUID = 1L;
	LetterDao letterDao;

	public LetterServiceImpl(LetterDao lDao) {
		super(lDao);
		this.dao = lDao;
		this.letterDao = lDao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Letter> getUserLetters(User user, String type) throws IllegalArgumentException {
		if (user == null) {
			throw new IllegalArgumentException("User can not be null");
		}
		if (type.isEmpty()) {
			throw new IllegalArgumentException("Type field can not be null");
		}
		return letterDao.getUserLetters(user, type);
	}

}
