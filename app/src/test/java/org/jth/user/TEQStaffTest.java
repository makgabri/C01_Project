package org.jth.user;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.Test;


import org.jth.databaseHelper.DatabaseInsertHelper;
import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.security.Authenticate;
import org.jth.security.AuthenticateImpl;

class TEQStaffTest {
	DatabaseInsertHelper dbi = new DatabaseInsertHelperImpl();
	Map<String, String> userMap = dbi.insertUser(Roles.TEQ.name(), "crystal.li@gmail.com", "a4y81^");
	String userID = userMap.keySet().iterator().next();
	String creationDate = userMap.values().iterator().next();
	User teqStaff = new TEQStaff("Crystal", "Li", Roles.TEQ, userID, "crystal.li@gmail.com");
	Authenticate authenticate = new AuthenticateImpl(userID);
	
	@Test
	void getRole() {
		assertEquals(Roles.TEQ, teqStaff.getRole());
	}
	
	@Test
	void getUserID() {
		assertEquals(userID, teqStaff.getUserId());
	}
	
	@Test
	void getEmail() {
		assertEquals("crystal.li@gmail.com", teqStaff.getEmail());
	}
	
	// TODO Need a test for when the user actually uploads 
	@Test
	void getIsUploadedNoUpload() {
		assertFalse(teqStaff.isUploaded());
	}
	
	@Test
	void getCreationDate() {
		assertEquals(creationDate, teqStaff.getCreationDate());
	}
	
	@Test
	void loginSuccessful() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		assertTrue(authenticate.authenticate("a4y81"));
	}
	
	@Test
	void loginFailure() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		assertFalse(authenticate.authenticate("123"));
	}
	
	@Test
	void getFirstName() {
		assertEquals("Crystal", ((TEQStaff) teqStaff).getFirstName());
	}
	
	@Test
	void getLastName() {
		assertEquals("Li", ((TEQStaff) teqStaff).getLastName());
	}

}
