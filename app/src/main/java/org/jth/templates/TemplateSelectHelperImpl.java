package org.jth.templates;

import java.sql.*;
import org.jth.databaseHelper.DatabaseDriver;

public class TemplateSelectHelperImpl {

  /**
   * Gets an object by field and unique iv
   * @param uniqueiv - unique identifier value of the record to be looked at
   * @param field - the column of data that is to be retrieved
   * @return - the data from the column 
   */
  public Object getValueFromField(Integer uniqueiv, String field) {
    Connection conn = DatabaseDriver.connectOrCreateDatabase();
    Statement stmt;
    String sql;
    ResultSet rs;
    Object result = null;
    
    sql = "SELECT * FROM TEMPLATE WHERE UNIQUE_IDENTIFIER_VALUE="
        + uniqueiv + ";";
    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs.next()) {
        result = rs.getObject(field);
      }
      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * Updates the value in the table
   * @param uniqueiv - unique identifier value of record to be updated
   * @param field - column to be updated
   * @param value - new value to be replaced
   * @return - true if successfull changed, false otherwise
   */
  public Boolean updateValue(Integer uniqueiv, String field, Object value) {
    Connection conn = DatabaseDriver.connectOrCreateDatabase();
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    String sql = "UPDATE TEMPLATE SET " + field + "='" + value+ "' WHERE "
        + "UNIQUEIV = '" + uniqueiv + "'";
    try {
      stmt.execute(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true;
  }

}
