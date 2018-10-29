package org.jth.databaseHelper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jth.user.Roles;
import org.jth.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DatabaseSelectHelperImplTest {
	DatabaseSelectHelper dbs = new DatabaseSelectHelperImpl();
	
	@DisplayName("get a user using their userId")
	@Test
	void selectUserByIDTest() {
		User user = dbs.getUser(dbs.getUserId("email@email.com"));
		assertTrue(user.getEmail().equals("email@email.com"));
	}
	
	@DisplayName("get a non existent user")
	@Test
	void nonExistentUserTest() {
		User user = dbs.getUser(dbs.getUserId("rumble@io.com"));
		assertTrue(user == null);
	}
	
	@DisplayName("get a roleId using the name of the role")
	@Test
	void getRoleIdTest() {
		int roleId = dbs.getRoleId(Roles.ORGANIZATION.name());
		int orgId = 1;
		assertTrue(roleId == orgId);
	}
	
	
}
