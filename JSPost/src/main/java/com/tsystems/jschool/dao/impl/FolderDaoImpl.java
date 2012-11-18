/**
 * 
 */
package com.tsystems.jschool.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.tsystems.jschool.auxiliary.QueryConstant;
import com.tsystems.jschool.dao.FolderDao;
import com.tsystems.jschool.model.Folder;
import com.tsystems.jschool.model.User;

/**
 * @author Lilia Abdulina
 * 
 */
public class FolderDaoImpl extends GenericDaoImpl<Folder> implements FolderDao {

	public FolderDaoImpl() {
		super(Folder.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tsystems.jschool.dao.FolderDao#getUserFolders(com.tsystems.jschool
	 * .entity.User)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Folder> getUserFolders(User user)
			throws IllegalArgumentException {
		return getEntityManager()
				.createNamedQuery(QueryConstant.FOLDER_SELECT_FROM_USER)
				.setParameter("user", user).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsystems.jschool.dao.FolderDao#getFolderByName(java.lang.String,
	 * com.tsystems.jschool.model.User)
	 */
	@Override
	public Folder getFolderByName(String address, User user)
			throws IllegalArgumentException {
		Query query = getEntityManager()
				.createNamedQuery(QueryConstant.FOLDER_SELECT_BY_NAME_AND_USER)
				.setParameter("name", address).setParameter("user", user);

		return getSingleResultOrNull(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tsystems.jschool.dao.FolderDao#getUserFoldersName(com.tsystems.jschool
	 * .entity.User)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUserFoldersName(User user)
			throws IllegalArgumentException {
		List<String> names = new ArrayList<String>();
		List<Folder> resultList = getEntityManager()
				.createNamedQuery(QueryConstant.FOLDER_SELECT_FROM_USER)
				.setParameter("user", user).getResultList();
		for (Folder f : resultList) {
			names.add(f.getName());
		}
		return names;
	}

}
