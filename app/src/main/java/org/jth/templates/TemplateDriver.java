package org.jth.templates;

import java.sql.*;
import org.jth.databaseHelper.DatabaseDriver;
import org.jth.exceptions.ConnectionFailedException;

public class TemplateDriver {

  /**
   * Creates a database with template.db as the file if one doesn't exist
   * @return the connection to the template database
   */
  public static Connection connectOrCreateDatabase() {
    Connection connection = null;
    try {
      connection = DatabaseDriver.connectOrCreateDatabase();
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
    if (!initializeTemplate(connection)) {
      throw new ConnectionFailedException();
    }
    return connection;
  }
  
  /**
   * 
   * @param connection the database for the temmplates
   * @return the connection passed in to continue if needed
   * @throws ConnectionFailedException If the table doesnt exist
   */
  public static Connection clear(Connection connection) throws ConnectionFailedException {
    if (!clearDatabase(connection)) {
      throw new ConnectionFailedException();
    }
    return connection;
  }
  
  public static boolean initializeTemplate(Connection connection) {
    Statement statement = null;
    
    try {
      statement = connection.createStatement();
      
      Template Employee = TemplateFormat.Employee();
      String sql = "CREATE TABLE TEMPLATE " 
          + "(" + Employee.toStringCreate() + ")";
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
          
          
          String sql = "DROP TABLE TEMPLATE;";
          statement.executeUpdate(sql);
          
          statement.close();
          return true;
          
        } catch (Exception e) {
          e.printStackTrace();
        }
        return false;
      }
  
}
