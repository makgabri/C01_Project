package org.jth.templates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import org.jth.databaseHelper.DatabaseDriver;

public class TemplateInsertHelperImpl implements TemplateInsertHelper {

  @Override
  /**
   * @param UniqueIdentifierValue - the records unique identifier value
   * @return roleid - IDK ask mohammad
   */
  public boolean insertUniqueIV(Integer uniqueidentifiervalue) {
    Connection conn = DatabaseDriver.connectOrCreateDatabase();
    PreparedStatement stmt = null;

    try {
      String sql = "INSERT INTO TEMPLATE VALUES (?);";
      stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stmt.setInt(1, uniqueidentifiervalue);
      stmt.executeUpdate();
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return true;
  }

  @Override
  /**
   * @param
   * @param itemmap - a map object containing the field as the key and th
   *         value of that field
   * @return true if successfully inserted and false otherwise due to wrong
   *          field name
   */
  // TODO Add methods to check key in item map corresponds to field as well
  //      as check the type of in field
  public boolean insertTemplateItems(Integer uniqueidentifiervalue,
      Map<String, Object> itemmap) {
    Connection conn = DatabaseDriver.connectOrCreateDatabase();
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    for (String key : itemmap.keySet()) {
      String sql = "UPDATE TEMPLATE SET " + key + "='" + itemmap.get(key)
                  + "' WHERE UNIQUEIV = '" + uniqueidentifiervalue + "'";
      try {
        stmt.execute(sql);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return true;
  }

}
