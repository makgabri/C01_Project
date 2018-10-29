package org.jth.security;

import java.security.MessageDigest;

public class PasswordHelpers {
	
	/**
	 * Hashes a password using the SHA-256 algorithm
	 * 
	 * @param password - the password before hashing
	 * @return a string representation of the hashed password
	 */
	public static String passwordHash(String password) {
		MessageDigest md;
	    try {
	      md = MessageDigest.getInstance("SHA-256");
	      md.update(password.getBytes("UTF-8"));
	      byte[] digest = md.digest();
	      return String.format("%064x", new java.math.BigInteger(1, digest));

	    } catch (Exception e) {
	      return null;
	    }
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
