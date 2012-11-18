package com.tsystems.jschool.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tsystems.jschool.model.Folder;

public class FolderDaoTest extends BaseDaoTestCase {
	@Autowired
	private FolderDao folderDao;

	@Test
	public void testGetFolderById() throws Exception {
		Folder folder = folderDao.get(1);
		assertTrue(folder != null);
	}

}
