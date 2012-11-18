package com.tsystems.jschool.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.tsystems.jschool.exception.DuplicateException;
import com.tsystems.jschool.model.Folder;
import com.tsystems.jschool.service.FolderService;


@ManagedBean(name = "folder")
@SessionScoped
public class FolderBean extends Bean {

	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{folderService}")
	private FolderService folderService;
	@ManagedProperty("#{user}")
	private UserBean userBean;
	
	private Folder folder = new Folder();
	private List<String> userFoldersName = new ArrayList<String>();

	@PostConstruct
	public void init() {
		userFoldersName = getFolderService().getUserFoldersName(getUserBean().getUser());
	}
	
	public String createFolder() {
		folder.setUser(userBean.getUser());
		try {
			Folder savedFolder = folderService.save(folder);
			userFoldersName.add(savedFolder.getName());
			return "/pages/index/letters.xhtml?faces-redirect=true";
		} catch(DuplicateException e) {
			addContextMessage("folder_exist_msg");
			return null;
		}
	}
	
	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public List<String> getUserFoldersName() {
		return userFoldersName;
	}

	public void setUserFoldersName(List<String> userFoldersName) {
		this.userFoldersName = userFoldersName;
	}

	public FolderService getFolderService() {
		return folderService;
	}

	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	

}
