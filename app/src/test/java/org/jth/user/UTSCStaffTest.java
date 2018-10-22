package org.jth.user;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.jth.security.PasswordHelpers;
import org.junit.jupiter.api.Test;
import org.jth.user.UTSCStaff;


class UTSCStaffTest {
	User utscStaff = new UTSCStaff("Sumit", "Kapal", "supal", "s.kapal@mail.utoronto.ca");

	@Test
	void logInSucessful() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO store hashed password into the database
		String databasePassword = PasswordHelpers.passwordHash("helloworld");
		assertTrue(utscStaff.logIn("supal", "helloworld"));
	}
	
	// This test should be false once the function to store passwords to the database is implemented
	@Test
	void logInFailure() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO store hashed password into database
		String databasePassword = PasswordHelpers.passwordHash("helloworld");
		assertFalse(utscStaff.logIn("supal", "123"));
	}
	
	@Test
	void getFirstName() {
		assertEquals("Sumit", ((UTSCStaff) utscStaff).getFirstName());
	}
	
	@Test
	void getLastName() {
		assertEquals("Kapal", ((UTSCStaff) utscStaff).getLastName());
	}

}
