package org.jth.templates;

import java.sql.*;
import java.util.ArrayList;

public class TemplateSelectHelperImpl {

  /**
   * Get an array of the tables found in the excel file
   * @param connection - connection to database
   * @return arrayList of tables not found in DatabaseDriver
   */
  public ArrayList<String> getTables(Connection connection) {
      ArrayList<String> result = new ArrayList<>();
      DatabaseMetaData md;
      try {
        md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        while (rs.next()) {
          if (!rs.getString(3).equals("ROLETYPES") &&
              !rs.getString(3).equals("USERPW") &&
              !rs.getString(3).equals("USERS")) {
            result.add(rs.getString(3));
          }
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return result;
  }
  
  /**
   * Get an array of the columns found in a table
   * @param connection - connection to database
   * @param templateType - table name
   * @return arrayList of columns from table
   */
  public ArrayList<String> getColumnFromTable(Connection connection,
      String templateType) {
      ArrayList<String> result = new ArrayList<>();
      DatabaseMetaData md;
      try {
          md = connection.getMetaData();
          ResultSet rs = md.getColumns(null, null, templateType, null);
          while (rs.next()) {
              result.add(rs.getString("COLUMN_NAME"));
          }
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return result;
  }
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
   * Searches for all Unique Identifier Values with condition in field in
   * template name
   * @param connection - connection to database
   * @param templateName - templateName to look in
   * @param field - field to be looked at 
   * @param condition - to compare to this value
   * @return - an array of all unique iv the contain condition in field in
   *            template name
   */
  public ArrayList<String> getUniqueIV(Connection connection, String templateName,
        String field, String condition) {
      ArrayList<String> result = new ArrayList<String>();
      Statement stmt;
      String sql;
      ResultSet rs;
      
      sql = "SELECT * FROM " + templateName;
      try {
        stmt = connection.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
          if (rs.getString(field).equals(condition)) {
            result.add(Integer.toString(rs.getInt("UNIQUE_IDENTIFIER_VALUE")));
          }
        }
        rs.close();
        stmt.close();
      } catch (SQLException e) {
          // Debug uncomment next line
          // e.printStackTrace();
          return result;
      }
      return result;
  }

  /**
   * 
   * @param connection - connection to database
   * @param templateName - templteName to look in
   * @param field - fieldto be looked at
   * @param condition - to compare to this value
   * @return - total number of items in template name with condition in field
   */
  public int getTotalFromField(Connection connection, String templateName,
      String field, String condition) {
    int result = 0;
    Connection conn = connection;
    Statement stmt;
    String sql;
    ResultSet rs;
    
    sql = "SELECT * FROM " + templateName;
    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        if (rs.getString(field).equals(condition)) {
          result += 1;
        }
      }
      rs.close();
      stmt.close();
    } catch (SQLException e) {
      // Debug uncomment next line
      // e.printStackTrace();
      return result;
    }
    return result;
  }
}
