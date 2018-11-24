package org.jth.templates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TemplateInsertHelperImpl {

  
  /**
   * @param templateType - the type of template to be created(i.e EMPLOYEE)
   * @param fieldData - an array of all the data
   * @return true if successfully inserted and false otherwise 
   */
  public boolean insertTemplateItems(Connection connection, String templateType,
      ArrayList<String> fieldData, Integer row) throws Exception, SQLException {
    // Create prepared statement
    PreparedStatement stmt = null;
    
    // If no such template exists, return false
    if (TemplateFormat.getTemplate(templateType) == null) {
      return false;
    }
    
    // Setup prepare statement
    String sql = "INSERT INTO "+ templateType + " VALUES (";
    for (int i = 0; i < fieldData.size(); i++) {
      sql += "?, ";
    }
    sql = sql.substring(0, sql.length() - 2) + ")";
    
    // Execute prepared statement
    try {
      stmt = connection.prepareStatement(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    for (int i = 1; i <= fieldData.size(); i++) {
        stmt.setString(i, fieldData.get(i-1));
    }
    
    try {
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    stmt.close();
    return true;
  }
  
  /**
   * Given the necessary data, function checks if data is valid
   * @param fieldType - type of field
   * @param fieldData - data in field
   * @return - true if valid, false otherwise
   */
  public boolean validateData(String fieldType, String fieldData,
      boolean empty) {
      if (!empty && fieldData.equals("")) {
          return true;
      }
      if (fieldType.equals("INTEGER")) {
          // check if integer
          if (!fieldData.matches("\\d+") &&
              !fieldData.matches("^\\d+(?:,\\d{3})*")) {
              return false;
          }
      } else if (fieldType.equals("DOUBLE")) {
          // check if double
          try { Double.parseDouble(fieldData);}
          catch (NumberFormatException e) { return false;}
      } else if (fieldType.equals("BOOLEAN")) {
          // check if boolean
          if (!fieldData.equals("Yes") && !fieldData.equals("No")) {
            return false;
          }
      } else if (fieldType.equals("DATE")) {
          // Check if Date
          DateFormat dtF = new SimpleDateFormat("YYYY-MM-DD");
          try {
              dtF.parse(fieldData);
          } catch(ParseException e) {
              return false;
          }
      }
    return true;
  }

}
