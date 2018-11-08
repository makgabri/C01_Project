package org.jth.databaseHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.jth.exceptions.ConnectionFailedException;

public class DatabaseDriver {
	
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
	  
	  public static Connection clear(Connection connection) throws ConnectionFailedException {
	    if (!clearDatabase(connection)) {
	      throw new ConnectionFailedException();
	    }
	    return connection;
	  }
	  
	  private static boolean initializeDatabase(Connection connection) {
	    Statement statement = null;
	    
	    try {
	      statement = connection.createStatement();
	      
	      
	      String sql = "CREATE TABLE USERS " 
	          + "(ID VARCHAR(64) PRIMARY KEY NOT NULL UNIQUE," 
	          + "ACCESS INTEGER NOT NULL,"
	          + "UPLOADED INTEGER NOT NULL,"
	          + "CREATIONDATE TEXT NOT NULL,"
	          + "ROLEID INTEGER NOT NULL,"
	          + "EMAIL VARCHAR(64) NOT NULL UNIQUE,"
	          + "FOREIGN KEY(ROLEID) REFERENCES ROLE(ID))";
	      statement.executeUpdate(sql);
	      
	      sql = "CREATE TABLE ROLETYPES "
	          + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
	          + "NAME TEXT NOT NULL)";
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
		      
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return false;
	  }
	  
	  public static boolean doesTableExists(Connection connection, String table) {
	    DatabaseMetaData dbm = connection.getMetaData();
        return false;
	  }

}
