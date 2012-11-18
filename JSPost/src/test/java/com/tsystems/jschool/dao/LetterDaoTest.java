package com.tsystems.jschool.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tsystems.jschool.model.Letter;

public class LetterDaoTest extends BaseDaoTestCase {
	@Autowired
	private LetterDao letterDao;

	@Test
	public void testPutAndGetLetterById() throws Exception {
		Letter letter = letterDao.get(1);
		assertTrue(letter != null);
	}
}
