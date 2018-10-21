package org.jth.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.jth.security.PasswordHelpers;

import org.junit.jupiter.api.Test;

class PasswordHelpersTest {

	@Test
	void passwordHashSimplePassword() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String password = "hello world";
		String hashedPassword = PasswordHelpers.passwordHash(password);
		System.out.println(hashedPassword);
	}

	@Test
	void comparePasswordTrue() {
		String password = "hello world";
		assertTrue(PasswordHelpers.comparePassword(password, "hello world"));
	}

	@Test
	void comparePasswordFalse() {
		String password = "hello world";
		assertFalse(PasswordHelpers.comparePassword(password, "Hello world"));
	}
	
}
