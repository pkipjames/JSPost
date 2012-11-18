package com.tsystems.jschool.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tsystems.jschool.dao.FolderDao;
import com.tsystems.jschool.exception.DuplicateException;
import com.tsystems.jschool.model.Folder;
import com.tsystems.jschool.model.User;
import com.tsystems.jschool.service.FolderService;

@Repository("folderService")
@Transactional
public class FolderServiceImpl extends GenericServiceImpl<Folder> implements
		FolderService {

	private static final long serialVersionUID = 1L;
	FolderDao folderDao;

	public FolderServiceImpl(FolderDao fDao) {
		super(fDao);
		this.dao = fDao;
		this.folderDao = fDao;
	}

	@Override
	public Folder save(Folder folder) throws IllegalArgumentException,
			DuplicateException {
		checkUserFolder(folder);
		return super.save(folder);
	}

	private void checkUserFolder(Folder folder) throws DuplicateException {
		if (getFolderByName(folder.getName(), folder.getUser()) != null) {
			throw new DuplicateException(String.format(
					"Folder \"%s\" already exist for user \"%s %s\"", folder
							.getName(), folder.getUser().getLastName(), folder
							.getUser().getFirstName()));
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<Folder> getUserFolders(User user)
			throws IllegalArgumentException {
		if (user == null) {
			throw new IllegalArgumentException("User can not be null");
		}
		return folderDao.getUserFolders(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> getUserFoldersName(User user)
			throws IllegalArgumentException {
		if (user == null) {
			throw new IllegalArgumentException("User can not be null");
		}
		return folderDao.getUserFoldersName(user);
	}

	@Override
	@Transactional(readOnly = true)
	public Folder getFolderByName(String address, User user)
			throws IllegalArgumentException {
		if (user == null) {
			throw new IllegalArgumentException("User can not be null");
		}
		if (address == null || address == "") {
			throw new IllegalArgumentException("Name field can not be null");
		}
		return folderDao.getFolderByName(address, user);
	}

}
