package org.jth.security;

import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class PasswordHelpers {
	
	/**
	 * Hashes a password using the SHA-256 algorithm
	 * 
	 * @param password - the password before hashing
	 * @return a string representation of the hashed password
	 * @throws NoSuchAlgorithmException - if the SHA-256 algorithm cannot be found
	 * @throws UnsupportedEncodingException - if UTF-8 encoding is not supported
	 */
	public static String passwordHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] passwordBytes = password.getBytes("UTF-8");
			md.update(passwordBytes);
			byte[] hashedPasswordBytes = md.digest(passwordBytes);
			return new String(hashedPasswordBytes, "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Could not get the SHA-256 algorithm");
		}
		return null;
	}
	
	/**
	 * Compares two passwords to see if they match
	 * 
	 * @param password1 - the first password
	 * @param password2 - the second password
	 * @return true if passwords match
	 */
	public static boolean comparePassword(String password1, String password2) {
		if (password1.equals(password2)) {
			return true;
		}
		return false;
	}

}
