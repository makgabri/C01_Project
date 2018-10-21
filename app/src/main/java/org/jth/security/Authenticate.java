package org.jth.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface Authenticate {	

	/** 
	 * Authenicates the user if their password matches the password storied in the database
	 * 
	 * @param password - the plain password of the user
	 * @return true if the authenication was successful
	 * @throws NoSuchAlgorithmException - if the SHA-256 algorithm is not found
	 * @throws UnsupportedEncodingException -if UTF-8 encoding is not supported
	 */
	public boolean authenicate(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
	/**
	 * Deauthenicates the user
	 */
	public void deauthenicate();
	
	/**
	 * Checks whether the user is authenicated
	 * 
	 * @return true if the user is authenicated
	 */
	public boolean isAuthenticated();

}
