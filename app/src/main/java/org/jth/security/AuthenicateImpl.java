package org.jth.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.jth.security.PasswordHelpers;

public class AuthenicateImpl implements Authenticate {
	private String userId;
	private boolean userIsAuthenicated = false;
	
	/**
	 * Initializes AuthenicateImpl with the user's id
	 * 
	 * @param userId
	 */
	public AuthenicateImpl(String userId) {
		this.userId = userId;
	}
	
	@Override
	public boolean authenicate(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String hashedPassword = PasswordHelpers.passwordHash(password);
		// TODO get database password
		String databasePassword = PasswordHelpers.passwordHash(password);
		if (PasswordHelpers.comparePassword(databasePassword, hashedPassword)) {
			this.userIsAuthenicated = true;
			return true;
		}
		return false;
	}
	
	@Override
	public void deauthenicate() {
		if (this.isAuthenticated()) {
			this.userIsAuthenicated = false;
		}
	}

	@Override
	public boolean isAuthenticated() {
		if (this.userIsAuthenicated == true) {
			return true;
		}
		return false;
	}
	
}
