package com.tsystems.jschool.auxiliary;

public abstract class QueryConstant {
	public static final String QUERY_SELECT_ALL = "SELECT x FROM %s x";
	
	public static final String USER_LOGIN = "getByLogin";
	public static final String USER_GET_ROLE = "getUserRole";
	public static final String USER_GET_BY_ADDRESS = "getUserByAddress";
	public static final String USER_GET_BY_PHONE = "getUserByPhone";
	public static final String USER_GET_BY_ADDRESS_OR_PHONE = "getUserByAddressORPhone";
	
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	public static final String ADDRESS_GET_USER = "getUserAddress";
	public static final String ADDRESS_GET_BY_NAME = "getAddressByName";
	
	public static final String FOLDER_SELECT_FROM_USER = "getFoldersByAddress";
	public static final String FOLDER_SELECT_BY_NAME_AND_USER = "getFolderByNameAndAddress";
	
	public static final String LETTER_GET_INBOX = "getInboxLetters";
	public static final String LETTER_GET_OUTBOX = "getOutboxLetters";
	public static final String LETTER_GET_TRASH = "getTrashLetters";
}
