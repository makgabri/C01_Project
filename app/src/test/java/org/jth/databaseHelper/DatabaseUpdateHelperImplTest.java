package org.jth.databaseHelper;
import org.jth.user.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Target;
import java.security.NoSuchAlgorithmException;

import org.jth.databaseHelper.DatabaseInsertHelper;
import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

class DatabaseUpdateHelperImplTest {
	DatabaseInsertHelper dbi = new DatabaseInsertHelperImpl();
	DatabaseUpdateHelper dbu = new DatabaseUpdateHelperImpl();
	DatabaseSelectHelper dbs = new DatabaseSelectHelperImpl();

	@Test
	void testChangeToSameRole()
	{
		String AliceID = dbs.getUserId("alice@janitor.edu.ca");
		dbu.updateUserRole(AliceID,Roles.TEQ.name());
		Roles AliceRole = (dbs.getUser(AliceID)).getRole();
		assertEquals(Roles.TEQ.name(),AliceRole.name());
	}

	@Test
	void testChangeRoleOnce()
	{
		String AliceID = dbs.getUserId("alice@janitor.edu.ca");
		dbu.updateUserRole(AliceID,Roles.UTSC.name());
		Roles AliceRole = (dbs.getUser(AliceID)).getRole();
		assertEquals(Roles.UTSC.name(),AliceRole.name());
	}

	@Test
	void testChangeRoleTwice()
	{
		String AliceID = dbs.getUserId("alice@janitor.edu.ca");
		dbu.updateUserRole(AliceID,Roles.UTSC.name());
		dbu.updateUserRole(AliceID,Roles.ORGANIZATION.name());
		Roles AliceRole = (dbs.getUser(AliceID)).getRole();
		assertEquals(Roles.ORGANIZATION.name(),AliceRole.name());
	}

	@Test
	void testChangeRoleFromTEQtoUTSCthenBackToTEQ()
	{
		String AliceID = dbs.getUserId("alice@janitor.edu.ca");
		dbu.updateUserRole(AliceID,Roles.UTSC.name());
		dbu.updateUserRole(AliceID,Roles.TEQ.name());
		Roles AliceRole = (dbs.getUser(AliceID)).getRole();
		assertEquals(Roles.TEQ.name(),AliceRole.name());
	}

	@Test
	void testChangeRoleOfTwoPeople()
	{
		String AliceID = dbs.getUserId("alice@janitor.edu.ca");
		String BobID = dbs.getUserId("bob@teq.com");
		dbu.updateUserRole(AliceID,Roles.TEQ.name());
		dbu.updateUserRole(BobID,Roles.ORGANIZATION.name());
		Roles AliceRole = (dbs.getUser(AliceID)).getRole();
		assertEquals(Roles.TEQ.name(),AliceRole.name());
		Roles BobRole = (dbs.getUser(BobID)).getRole();
		assertEquals(Roles.ORGANIZATION.name(),BobRole.name());
	}

	@Test
	void testChangeRoleOfTwoPeopleToSameRole()
	{
		String AliceID = dbs.getUserId("alice@janitor.edu.ca");
		String BobID = dbs.getUserId("bob@teq.com");
		dbu.updateUserRole(AliceID,Roles.ORGANIZATION.name());
		dbu.updateUserRole(BobID,Roles.ORGANIZATION.name());
		Roles AliceRole = (dbs.getUser(AliceID)).getRole();
		assertEquals(Roles.ORGANIZATION.name(),AliceRole.name());
		Roles BobRole = (dbs.getUser(AliceID)).getRole();
		assertEquals(Roles.ORGANIZATION.name(),BobRole.name());
	}

	@Test
	void testSwapRoleOfTwoPeople()
	{
		String AliceID = dbs.getUserId("alice@janitor.edu.ca");
		String BobID = dbs.getUserId("bob@teq.com");
		dbu.updateUserRole(AliceID,Roles.UTSC.name());
		dbu.updateUserRole(BobID,Roles.TEQ.name());
		dbu.updateUserRole(AliceID,Roles.TEQ.name());
		dbu.updateUserRole(BobID,Roles.UTSC.name());
		Roles AliceRole = (dbs.getUser(AliceID)).getRole();
		assertEquals(Roles.TEQ.name(),AliceRole.name());
		Roles BobRole = (dbs.getUser(BobID)).getRole();
		assertEquals(Roles.UTSC.name(),BobRole.name());
	}
}
