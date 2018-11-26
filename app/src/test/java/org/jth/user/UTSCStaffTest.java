package org.jth.user;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.jth.databaseHelper.DatabaseInsertHelper;
import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.databaseHelper.DatabaseSelectHelper;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;
import org.jth.security.AuthenticateImpl;
import org.jth.security.PasswordHelpers;
import org.junit.jupiter.api.Test;
import org.jth.user.UTSCStaff;


class UTSCStaffTest {

	DatabaseInsertHelper dbi = new DatabaseInsertHelperImpl();
	DatabaseSelectHelper dbs = new DatabaseSelectHelperImpl();
	//Map<String, String> userMap = dbi.insertUser(Roles.UTSC.name(), "1232314", "123");
	String userId = new DatabaseSelectHelperImpl().getUserId("1232314");

	User utscStaff = new UTSCStaff("Sumit", "Kapal", "supal", "s.kapal@mail.utoronto.ca", dbs.getCreationDate(userId));
	AuthenticateImpl user = new AuthenticateImpl(userId);

	@Test
	void logInSucessful() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO store hashed password into the database
		String databasePassword = PasswordHelpers.passwordHash("123");
		assertTrue(utscStaff.logIn(userId, "123"));
	}
	
	// This test should be false once the function to store passwords to the database is implemented
	@Test
	void logInFailure() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// TODO store hashed password into database
		String databasePassword = PasswordHelpers.passwordHash("123");
		assertFalse(utscStaff.logIn(userId, "123323"));
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
