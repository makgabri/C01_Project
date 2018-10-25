package org.jth.security;

import org.jth.security.PasswordHelpers;
import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;


public class AuthenticateImpl implements Authenticate {
	private String userId;
	private boolean userIsAuthenticated = false;
	
	/**
	 * Initializes AuthenicateImpl with the user's id
	 * 
	 * @param userId - the user's id used for authentication
	 */
	public AuthenticateImpl(String userId) {
		this.userId = userId;
	}
	
	public boolean authenticate(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String hashedPassword = PasswordHelpers.passwordHash(password);
		// TODO get database password
		String databasePassword = PasswordHelpers.passwordHash(password);
		if (PasswordHelpers.comparePassword(databasePassword, hashedPassword)) {
			this.userIsAuthenticated = true;
			return true;
		}
		return false;
	}
	
	public void deauthenticate() {
		if (this.isAuthenticated()) {
			this.userIsAuthenticated = false;
		}
	}

	public boolean isAuthenticated() {
		if (this.userIsAuthenticated == true) {
			return true;
		}
		return false;
	}
	
}
