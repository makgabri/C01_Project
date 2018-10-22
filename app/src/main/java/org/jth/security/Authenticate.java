package org.jth.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface Authenticate {	

	/** 
	 * Authenticates the user if their password matches the password storied in the database
	 * 
	 * @param password - the plain password of the user
	 * @return true if the authentication was successful
	 * @throws NoSuchAlgorithmException - if the SHA-256 algorithm is not found
	 * @throws UnsupportedEncodingException -if UTF-8 encoding is not supported
	 */
	public boolean authenticate(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
	/**
	 * Deauthenticates the user
	 */
	public void deauthenticate();
	
	/**
	 * Checks whether the user is authenticated
	 * 
	 * @return true if the user is authenticated
	 */
	public boolean isAuthenticated();

}
