package com.csc510.resa.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Object stored in MongoDB
 * 
 * @author Steven Green
 */
@Document
public class UserDocument {

	@Id
	private String username;
	private String passwordHash;
	private int salt;

	/**
	 * Constructs UserDocument object with all fields
	 * 
	 * @param username     The user's username
	 * @param passwordHash The salted hash for the user's password
	 * @param salt         The salt for the password's hash
	 */
	public UserDocument(String username, String passwordHash, int salt) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.salt = salt;
	}

	/**
	 * Gets the user's username
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the user's username
	 * 
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the user's password hash
	 * 
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * Sets the user's password hash
	 * 
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * Gets the user's salt for the password hash
	 * 
	 * @return the salt
	 */
	public int getSalt() {
		return salt;
	}

	/**
	 * Sets the salt for the user's password hash
	 * 
	 * @param salt the salt to set
	 */
	public void setSalt(int salt) {
		this.salt = salt;
	}
}
