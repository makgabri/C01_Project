package org.jth.templates;

import java.sql.*;
import org.jth.exceptions.ConnectionFailedException;

public class TemplateDriver {

  /**
   * This will initialize the database, or throw a ConnectionFailedException.
   * @param connection the database you'd like to write the tables to.
   * @return the connection you passed in, to allow you to continue.
   * @throws ConnectionFailedException If the tables couldn't be initialized, throw
   */
  public static Connection initialize(Connection connection,
      String templateType)
          throws ConnectionFailedException {
    if (!initializeTemplate(connection, templateType)) {
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
  public static Connection clear(Connection connection)
      throws ConnectionFailedException {
    if (!clearDatabase(connection)) {
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
      String templateType) {
    Statement statement = null;
    
    if (doesTableExist(connection, templateType)) {
      return true;
    }
    
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
  private static boolean clearDatabase(Connection connection) {
        Statement statement = null;
        TemplateSelectHelperImpl tsh = new TemplateSelectHelperImpl();
        try {
          statement = connection.createStatement();
          for (String templateType : tsh.getTables(connection)) {
            String sql = "DROP TABLE "+ templateType;
            System.out.println("Dropping " + templateType);
            statement.executeUpdate(sql);
          }
          
          statement.close();
          return true;
          
        } catch (Exception e) {
          e.printStackTrace();
        }
        return false;
      }
  
  private static boolean doesTableExist(Connection connection, String templateType) {
    DatabaseMetaData dbm = null;
    try {
      dbm = connection.getMetaData();
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    ResultSet tables = null;
    try {
      tables = dbm.getTables(null, null, templateType, null);
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    try {
      if (tables.next()) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }
  
}
