package org.jth.templates;

import java.sql.*;
import org.jth.databaseHelper.DatabaseDriver;

public class TemplateSelectHelperImpl implements TemplateSelectHelper {

  @Override
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

  @Override
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
