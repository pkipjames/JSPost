package com.tsystems.jschool.web.bean;

import static com.tsystems.jschool.auxiliary.MessageConstant.INBOX;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.tsystems.jschool.model.Folder;
import com.tsystems.jschool.model.Letter;
import com.tsystems.jschool.model.User;
import com.tsystems.jschool.model.Letter.LetterStatus;
import com.tsystems.jschool.service.FolderService;
import com.tsystems.jschool.service.LetterService;
import com.tsystems.jschool.service.UserService;

@ManagedBean(name = "letter")
@SessionScoped
public class LetterBean extends Bean {

	private static final long serialVersionUID = 1L;
	private String addressUserTo;
	private Letter letter = new Letter();
	private Letter letterAnswer = new Letter();
	private List<Letter> letters = new ArrayList<Letter>();
	private String letterType;
	private String folderName;
	private String folderToMove;

	@ManagedProperty("#{userService}")
	private UserService userService;
	@ManagedProperty(value = "#{user}")
	private UserBean userBean;
	@ManagedProperty("#{letterService}")
	private LetterService letterService;
	@ManagedProperty("#{folderService}")
	private FolderService folderService;

	@PostConstruct
	public void init() {
		setLetterType(INBOX);
		setLettersFromType(getLetterType());
	}

	public String addLetterPrepare() {
		clearFields(letter);
		return "/pages/index/addLetter.xhtml?faces-redirect=true";
	}

	public String sendLetter() {
		User userTo = userService.getUserByAddress(addressUserTo);

		if (userTo != null) {
			letter.setFrom(userBean.getUser());
			letter.setTo(userTo);
			letter.setDate(Calendar.getInstance());
			letter.setStatus(LetterStatus.NEW);

			if (letter.getSubject() == "") {
				letter.setSubject("No subject");
			}
			getLetterService().save(letter);
			clearFields(letter);

			return "/pages/index/letters.xhtml?faces-redirect=true";
		}
		addContextMessage("address_not_found_msg");
		return null;
	}

	public String sendLetterAnswer() {
		letterAnswer.setFrom(userBean.getUser());
		letterAnswer.setTo(letter.getFrom());
		letterAnswer.setDate(Calendar.getInstance());

		letterService.save(getLetterAnswer());

		clearFields(letterAnswer);
		return "/pages/index/letters.xhtml?faces-redirect=true";
	}

	public String getUserLetters() {
		String parameter = getParameter("letterType");
		setFolderName(null);
		setLetterType(parameter);
		setLettersFromType(getLetterType());
		return "/pages/index/letters.xhtml?faces-redirect=true";
	}

	public String getUserLettersByFolder() {
		String parameter = getParameter("folderName");
		Folder folder = folderService.getFolderByName(parameter,
				userBean.getUser());

		setLetterType("other");
		folderName = parameter;

		letters = folder.getLetters();
		return "/pages/index/letters.xhtml?faces-redirect=true";
	}

	public String readLetter() {
		String param = getParameter("idLetter");
		letter = letterService.getEntityById(Integer.parseInt(param));
		if (!userBean.getUser().equals(letter.getFrom())
				|| userBean.getUser().equals(letter.getTo())) {
			letter.setStatus(LetterStatus.READ);
			letterService.save(letter);
		}
		letterAnswer.setSubject(String.format("Re: %s", letter.getSubject()));

		return "/pages/index/readLetter.xhtml?faces-redirect=true";
	}

	public String deleteLetter() {
		letter.getWhoDeleted().add(userBean.getUser());
		letterService.save(letter);
		return "/pages/index/letters.xhtml?faces-redirect=true";
	}

	public String getRowCssClasses() {
		StringBuilder resultString = new StringBuilder();
		for (Letter l : letters) {
			if (l.getStatus() == LetterStatus.NEW) {
				resultString.append("new_letter");
			} else {
				resultString.append("read_letter");
			}
			resultString.append(", ");
		}

		return resultString.toString();
	}

	public String moveLetterToFolder() {
		Folder folder = folderService.getFolderByName(folderToMove,
				userBean.getUser());
		letter.setFolder(folder);
		letterService.save(letter);
		return "/pages/index/letters.xhtml?faces-redirect=true";
	}

	private void clearFields(Letter l) {
		l.setDate(null);
		l.setFolder(null);
		l.setFrom(null);
		l.setId(null);
		l.setMessage(null);
		l.setSubject(null);
		l.setTo(null);
	}

	public void setLettersFromType(String type) {
		this.letters = letterService.getUserLetters(userBean.getUser(), type);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getAddressUserTo() {
		return addressUserTo;
	}

	public void setAddressUserTo(String address) {
		this.addressUserTo = address;
	}

	public Letter getLetter() {
		return letter;
	}

	public void setLetter(Letter letter) {
		this.letter = letter;
	}

	public LetterService getLetterService() {
		return letterService;
	}

	public void setLetterService(LetterService letterService) {
		this.letterService = letterService;
	}

	public List<Letter> getLetters() {
		return letters;
	}

	public String getLetterType() {
		return letterType;
	}

	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}

	public Letter getLetterAnswer() {
		return letterAnswer;
	}

	public void setLetterAnswer(Letter letterAnswer) {
		this.letterAnswer = letterAnswer;
	}

	public FolderService getFolderService() {
		return folderService;
	}

	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFolderToMove() {
		return folderToMove;
	}

	public void setFolderToMove(String folderToMove) {
		this.folderToMove = folderToMove;
	}

}
