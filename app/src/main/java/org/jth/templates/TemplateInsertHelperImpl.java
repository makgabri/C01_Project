package org.jth.templates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.jth.databaseHelper.DatabaseDriver;
import java.util.ArrayList;

public class TemplateInsertHelperImpl {

  
  /**
   * @param templateType - the type of template to be created(i.e EMPLOYEE)
   * @param fieldData - an array of all the data
   * @return true if successfully inserted and false otherwise 
   */
<<<<<<< HEAD
  public boolean insertTemplateItems(ArrayList<String> row) throws Exception, SQLException {
    Connection conn = TemplateDriver.connectOrCreateDatabase();
=======
  public boolean insertTemplateItems(String templateType,
      ArrayList<String> fieldData) throws Exception, SQLException {
    // Create Connection and prepared statement
    Connection conn = DatabaseDriver.connectOrCreateDatabase();
>>>>>>> de9e72432844b95fcdcea720556c5a445727fcf6
    PreparedStatement stmt = null;
    
    // Get template for requested template type
    Template template = TemplateFormat.getTemplate(templateType);
    // If no such template exists, return false
    if (template == null) {
      return false;
    }
    
    // Get array type from template
    ArrayList<String> fieldType = template.getFieldTypeList();
    
    // Setup prepare statement
    String sql = "INSERT INTO "+ templateType + " VALUES (";
    for (int i = 0; i < fieldData.size(); i++) {
      sql += "?, ";
    }
    sql = sql.substring(0, sql.length() - 2) + ")";
    
    // Execute prepared statement
    try {
      stmt = conn.prepareStatement(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    for (int i = 1; i <= fieldData.size(); i++) {
      if (fieldType.get(i-1).equals("INTEGER")) {
        if (fieldData.get(i-1).equals("")) {
          stmt.setInt(i, 0);
        } else {
          stmt.setInt(i, Integer.parseInt(fieldData.get(i-1)));
        }
      } else if (fieldType.get(i-1).equals("BOOLEAN")) {
        if (fieldData.get(i-1).equals("")) {
          stmt.setBoolean(i, false);
        } else {
          stmt.setBoolean(i, Boolean.parseBoolean(fieldData.get(i-1)));
        }
      } else if (fieldType.get(i-1).equals("LONGVARCHAR")) {
        stmt.setString(i, fieldData.get(i-1));
      } else if(fieldType.get(i-1).equals("DATE")) {
        if (fieldData.get(i-1).equals("")) {
          stmt.setNull(i, java.sql.Types.DATE);
        } else {
          stmt.setDate(i, java.sql.Date.valueOf(fieldData.get(i-1)));
        }
      }
    }
    
    try {
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true;
  }

}
