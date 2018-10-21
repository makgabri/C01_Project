package org.jth.cscc01.app;

import java.sql.Connection;

import org.jth.databaseHelper.DatabaseDriver;
import org.jth.exceptions.ConnectionFailedException;

public class App {
	
	private static void initializeDatabase(Connection connection) throws ConnectionFailedException {
		// TODO create first user
		DatabaseDriver.initialize(connection);
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
