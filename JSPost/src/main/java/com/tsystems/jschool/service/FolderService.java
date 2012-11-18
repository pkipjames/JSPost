package com.tsystems.jschool.service;

import java.util.List;

import com.tsystems.jschool.model.Folder;
import com.tsystems.jschool.model.User;

/**
 * GenericService interface for actions with User's folders
 * 
 * @author Lilia Abdulina
 */
public interface FolderService extends GenericService<Folder> {

	/**
	 * Get all folders for the specified user
	 * 
	 * @param user
	 *            specified user
	 * @return List of folders if success, or empty list if count of elements =
	 *         0
	 */
	List<Folder> getUserFolders(User user) throws IllegalArgumentException;

	/**
	 * Get one folder specified by its name and address.
	 * 
	 * @param name
	 *            folder's name
	 * @param address
	 *            folder's address
	 * @return Folder if it found, else null
	 */
	Folder getFolderByName(String address, User user)
			throws IllegalArgumentException;

	/**
	 * Get the list of folder names for specified user.
	 */
	List<String> getUserFoldersName(User user) throws IllegalArgumentException;
}
