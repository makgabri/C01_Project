package org.jth.cscc01.app;

import java.sql.Connection;

import org.jth.GUI.Windows.StarterWindow;
import org.jth.databaseHelper.DatabaseDriver;
import org.jth.databaseHelper.DatabaseInsertHelper;
import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.databaseHelper.DatabaseSelectHelper;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;
import org.jth.exceptions.ConnectionFailedException;
import org.jth.user.Roles;
import org.jth.user.TEQStaff;
import org.jth.user.User;

public class App {
	
	private static void initializeDatabase(Connection connection) throws ConnectionFailedException {
		// TODO create first user
		DatabaseDriver.initialize(connection);
		DatabaseInsertHelper dbInsert = new DatabaseInsertHelperImpl();
		int orgRoleId = dbInsert.insertRole(Roles.ORGANIZATION.name());
		if (orgRoleId < 0) {
			System.out.println("Failed to insert ORGANIZATION role");
		}
		int teqRoleId = dbInsert.insertRole(Roles.TEQ.name());
		if (teqRoleId < 0) {
			System.out.println("Failed to insert TEQ role");
		}
		int utscRoleId = dbInsert.insertRole(Roles.UTSC.name());
		if (utscRoleId < 0) {
			System.out.println("Failed to insert UTSC role");
		}
		dbInsert.insertUser(Roles.ORGANIZATION.name(), "email@email.com", "password");
		dbInsert.insertUser(Roles.UTSC.name(), "1232314", "123");
		dbInsert.insertUser(Roles.TEQ.name(), "crystal.li@gmail.com", "a4y81^");
		dbInsert.insertUser(Roles.ORGANIZATION.name(), "FGH@gmail.com", "123");
		dbInsert.insertUser(Roles.TEQ.name(),"alice@janitor.edu.ca", "123");
		dbInsert.insertUser(Roles.UTSC.name(),"bob@teq.com", "123");
		dbInsert.insertUser(Roles.ORGANIZATION.name(),"charlie@random.io", "123");
	}
	
	public static void displayStarterPage() {
        StarterWindow starterWindow = new StarterWindow();
    }
	
	/*
    public static void main( String[] args ) {
    	Connection connection = DatabaseDriver.connectOrCreateDatabase();
        if (args.length > 0) {
	    	if (args[0].equals("-2")) {
	    		try {
					DatabaseDriver.clear(connection);
					System.out.println("All tables have been dropped");
				} catch (ConnectionFailedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	else if (args[0].equals("-1")) {
	        	// initialize database here (don't forget to add the first user, etc)
	        	try {
					initializeDatabase(connection);
					System.out.println("Database has been initialized");
				} catch (ConnectionFailedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
        } else {
        	System.out.println("Main app");
        	DatabaseSelectHelper dbs = new DatabaseSelectHelperImpl();
        	
        	User user = dbs.getUser(dbs.getUserId("email@email.com"));
        	System.out.println(user);
        	System.out.println(dbs.getUserId("email@email.com"));
        	System.out.println(dbs.getRoleId(Roles.TEQ.name()));
        	System.out.println(dbs.getUser(dbs.getUserId("alice@janitor.edu.ca")).getRole());
        }
        displayStarterPage();
    }
    */
}
