package org.jth.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.jth.databaseHelper.DatabaseInsertHelper;
import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;
import org.junit.jupiter.api.Test;

class AuthenticateImplTest {
	DatabaseInsertHelper dbi = new DatabaseInsertHelperImpl();
	//Map<String, String> userMap = dbi.insertUser(Roles.UTSC.name(), "1232314", "123");
	String userId = new DatabaseSelectHelperImpl().getUserId("1232314");
	
	AuthenticateImpl user = new AuthenticateImpl(userId);
	
	@Test
	void authenticateSuccess() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		assertTrue(user.authenticate("123"));
	}
	
	@Test
	void authenticateFail() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO Check once authenticate uses database info instead of having it true all the time
		assertFalse(user.authenticate("1234"));
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
