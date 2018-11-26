package org.jth.databaseHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.jth.exceptions.ConnectionFailedException;

public class DatabaseDriver {
	
	/**
	 * Connects to the database if it is already set up.
	 * @return Connection to the database
	 */
	public static Connection connectOrCreateDatabase() {
	    Connection connection = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      connection = DriverManager.getConnection("jdbc:sqlite:app.db");
	    } catch (Exception e) {
	      System.out.println("Something went wrong with your connection! see below details: ");
	      e.printStackTrace();
	    }
	    
	    return connection;
	  }
	
	/**
	   * This will initialize the database, or throw a ConnectionFailedException.
	   * @param connection the database you'd like to write the tables to.
	   * @return the connection you passed in, to allow you to continue.
	   * @throws ConnectionFailedException If the tables couldn't be initialized, throw
	   */
	  public static Connection initialize(Connection connection) throws ConnectionFailedException {
	    if (!initializeDatabase(connection)) {
	      throw new ConnectionFailedException();
	    }
	    return connection;
	  }
	  
	  /**
	   * Drops all the tables that are in the database.
	   * @param connection - a connection to the database.
	   * @return A Connection object that is the same as the parameter connection.
	   * @throws ConnectionFailedException if the database cannot be connected to
	   */
	  public static Connection clear(Connection connection) throws ConnectionFailedException {
	    if (!clearDatabase(connection)) {
	      throw new ConnectionFailedException();
	    }
	    return connection;
	  }
	  
	  /**
	   * Creates all the database tables that will be needed.
	   * @param connection - a connection to the database where the tables will be created.
	   * @return A boolean for whether or not the tables were successfully created.
	   */
	  private static boolean initializeDatabase(Connection connection) {
	    Statement statement = null;
	    
	    try {
	      statement = connection.createStatement();
	      
	      
	      String sql = "CREATE TABLE ROLETYPES "
	              + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
	              + "NAME TEXT NOT NULL)";
	      statement.executeUpdate(sql);
	      
	      sql = "CREATE TABLE USERS " 
		          + "(ID VARCHAR(64) PRIMARY KEY NOT NULL UNIQUE," 
		          + "ACCESS INTEGER NOT NULL,"
		          + "UPLOADED INTEGER NOT NULL,"
		          + "CREATIONDATE TEXT NOT NULL,"
		          + "ROLEID INTEGER NOT NULL,"
		          + "EMAIL VARCHAR(64) NOT NULL UNIQUE,"
		          + "FOREIGN KEY(ROLEID) REFERENCES ROLETYPES(ID));";
	      statement.executeUpdate(sql);
	  
	      sql = "CREATE TABLE USERPW " 
	          + "(USERID VARCHAR(64) NOT NULL UNIQUE,"
	          + "PASSWORD CHAR(64)," 
	          + "FOREIGN KEY(USERID) REFERENCES USER(ID))";
	      statement.executeUpdate(sql);
	      
	      statement.close();
	      return true;
	      
	    } catch (Exception e) {
	    	e.printStackTrace();
		}
	    return false;
	  }
	  
	  /**
	   * Drops the tables in the database.
	   * @param connection - a connection to the database
	   * @return True or false depending on whether the table dropping was successful
	   */
	  private static boolean clearDatabase(Connection connection) {
		    Statement statement = null;
		    
		    try {
		      statement = connection.createStatement();
		      
		      
		      String sql = "DROP TABLE USERS;";
		      statement.executeUpdate(sql);
		      
		      sql = "DROP TABLE ROLETYPES;";
		      statement.executeUpdate(sql);
		  
		      sql = "DROP TABLE USERPW;";
		      statement.executeUpdate(sql);
		      
		      statement.close();
		      return true;
		      
		    } catch (Exception e) {}
		    return false;
	  }

}
