package org.jth.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.jth.security.AuthenticateImpl;

import org.junit.jupiter.api.Test;

class AuthenticateImplTest {
	AuthenticateImpl user = new AuthenticateImpl("alice123");
	
	@Test
	void authenticateSuccess() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		assertTrue(user.authenticate("123"));
	}
	
	@Test
	void authenticateFail() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Check once authenticate uses database info instead of having it true all the time
		assertFalse(user.authenticate("123"));
	}
	
	@Test
	void deauthenticateSuccess() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		user.authenticate("123");
		user.deauthenticate();
		assertFalse(user.isAuthenticated());
	}
	
	@Test
	void deauthenticateAlreadyDeAuthenticated() {
		user.deauthenticate();
		assertFalse(user.isAuthenticated());
	}
	
	@Test
	void isAuthenticatedAuthenticated() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		user.authenticate("123");
		assertTrue(user.isAuthenticated());
	}
	
	@Test
	void isAuthenticatedNotAuthenticated() throws NoSuchAlgorithmException {
		assertFalse(user.isAuthenticated());
	}

}
