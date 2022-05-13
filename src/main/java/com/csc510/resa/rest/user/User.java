package com.csc510.resa.rest.user;

/**
 * Object containing information about a user Primarily used for request body
 * parameters
 * 
 * @author Steven Green
 */
public class User {

	private String username;
	private String password;

	/**
	 * Default constructor
	 */
	public User() {

	}

	/**
	 * Constructs UserDocument object with all fields
	 * 
	 * @param username The user's username
	 * @param password The user's unhashed password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
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
	 * Gets the user's password
	 * 
	 * @return the the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password hash
	 * 
	 * @param password the password to set
	 */
	public void setPasswordHash(String password) {
		this.password = password;
	}
}
