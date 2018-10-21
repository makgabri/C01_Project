package org.jth.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.jth.security.AuthenicateImpl;

import org.junit.jupiter.api.Test;

class AuthenicateImplTest {
	AuthenicateImpl user = new AuthenicateImpl("alice123");
	
	@Test
	void authenicateSuccess() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		assertTrue(user.authenicate("123"));
	}
	
	@Test
	void authenicateFail() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Check once authenicate uses database info instead of having it true all the time
		assertFalse(user.authenicate("123"));
	}
	
	@Test
	void deauthenicateSuccess() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		user.authenicate("123");
		user.deauthenicate();
		assertFalse(user.isAuthenticated());
	}
	
	@Test
	void deauthenicateAlreadyDeAuthenticated() {
		user.deauthenicate();
		assertFalse(user.isAuthenticated());
	}
	
	@Test
	void isAuthenicatedAuthenicated() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		user.authenicate("123");
		assertTrue(user.isAuthenticated());
	}
	
	@Test
	void isAuthenicatedNotAuthenicated() throws NoSuchAlgorithmException {
		assertFalse(user.isAuthenticated());
	}

}
