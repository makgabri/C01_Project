package org.jth.templates;

import java.sql.*;
import java.util.ArrayList;
import org.jth.exceptions.ConnectionFailedException;

public class TemplateDriver {

  /**
   * This will initialize the database, or throw a ConnectionFailedException.
   * @param connection the database you'd like to write the tables to.
   * @return the connection you passed in, to allow you to continue.
   * @throws ConnectionFailedException If the tables couldn't be initialized, throw
   */
  public static Connection initialize(Connection connection,
      String templateType, ArrayList<String> fieldType)
          throws ConnectionFailedException {
    if (!initializeTemplate(connection, templateType, fieldType)) {
      throw new ConnectionFailedException();
    }
    return connection;
  }
  
  /**
   * 
   * @param connection the database for the temmplates
   * @param templateType depending on type of template to be created
   * @return the connection passed in to continue if needed
   * @throws ConnectionFailedException If the table doesnt exist
   */
  public static Connection clear(Connection connection, String templateType)
      throws ConnectionFailedException {
    if (!clearDatabase(connection, templateType)) {
      throw new ConnectionFailedException();
    }
    return connection;
  }
  
  /**
   * 
   * @param connection - the connection of the database
   * @param templateType - the template that is to be created (i.e EMPLOYEE)
   * @return true if successfully created
   */
  private static boolean initializeTemplate(Connection connection,
      String templateType, ArrayList<String> fieldType) {
    Statement statement = null;
    
    try {
      statement = connection.createStatement();
      
      Template template = TemplateFormat.getTemplate(templateType);
      String sql = "CREATE TABLE " + template.getName()
          + " (" + template.toStringCreate() + ")";
      statement.executeUpdate(sql);
      
      statement.close();
      return true;
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
  
  /**
   * 
   * @param connection - the connection of the database
   * @param templateType - the template that is to be dropped (i.e EMPLOYEE)
   * @return true if successfully dropped
   */
  private static boolean clearDatabase(Connection connection,
      String templateType) {
        Statement statement = null;
        
        try {
          statement = connection.createStatement();
          
          
          String sql = "DROP TABLE "+ templateType +";";
          statement.executeUpdate(sql);
          
          statement.close();
          return true;
          
        } catch (Exception e) {
          e.printStackTrace();
        }
        return false;
      }
  
}
