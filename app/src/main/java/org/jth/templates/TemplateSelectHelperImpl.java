package org.jth.templates;

import java.sql.*;
import org.jth.databaseHelper.DatabaseDriver;

public class TemplateSelectHelperImpl {

  /**
   * Gets an object by field and unique iv
   * @param connection - connection to the database
   * @param uniqueiv - unique identifier value of the record to be looked at
   * @param templateName - template to get data from
   * @param field - the column of data that is to be retrieved
   * @return - the data from the column 
   */
  public String getValueFromField(Connection connection,
      Integer uniqueiv, String templateName, String field) {
    Connection conn = connection;
    Statement stmt;
    String sql;
    ResultSet rs;
    Object item = null;
    
    sql = "SELECT " + field + " FROM "+ templateName +" WHERE UNIQUE_IDENTIFIER_VALUE="
        + uniqueiv + ";";
    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      if (rs.next()) {
        item = rs.getObject(field);
      }
      rs.close();
      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    String result = (item == null ? null : item.toString());
    return result;
  }

  /**
   * 
   * @param connection - connection to database
   * @param templateName - templteName to look in
   * @param field - field item of field to be looked at
   * @param condition - to compare to this value
   * @return - total number of items in template name with condition in field
   */
  public int getTotalFromField(Connection connection, String templateName,
      Field field, String condition) {
    int result = 0;
    Connection conn = connection;
    Statement stmt;
    String sql;
    ResultSet rs;
    Object item;
    String temp;
    
    sql = "SELECT" + field.toString() + "FROM " + templateName;
    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        item = rs.getObject(field.toString());
        temp = (item == null ? null : item.toString());
        if (temp.equals(condition)) {
          result += 1;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
}
