package org.jth.cscc01.app;

import java.sql.Connection;

import org.jth.databaseHelper.DatabaseDriver;
import org.jth.databaseHelper.DatabaseInsertHelper;
import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.exceptions.ConnectionFailedException;
import org.jth.user.Roles;

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
		dbInsert.insertUser(Roles.TEQ.name(), "email@email.com", "password");
	}
	
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
        }
    }
}
