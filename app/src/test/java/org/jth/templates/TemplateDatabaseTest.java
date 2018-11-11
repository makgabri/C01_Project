package org.jth.templates;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jth.databaseHelper.DatabaseDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TemplateDatabaseTest {
    TemplateInsertHelperImpl dbi = new TemplateInsertHelperImpl();
    Connection connection = DatabaseDriver.connectOrCreateDatabase();
    
    @BeforeEach
    public void setUp() throws SQLException {
      try {
        TemplateDriver.initialize(connection, "EMPLOYEE");
      } catch (Exception e) {
        fail("Should not throw any exception");
      }
    }
 
    @AfterEach
    public void cleanUp() {
      try {
        TemplateDriver.clear(connection, "EMPLOYEE");
      } catch (Exception e) {
        fail("Should not throw any exception");
      }
    }
    
    @Test
    void testInsertUniqueIV() throws Exception {
      ArrayList<String> list = new ArrayList<String>();
      list.add(""); list.add(""); list.add("FOSS/GCMS Client ID");
      list.add("123123123");
      list.add("1978-05-20"); list.add("A1A 2B2"); list.add("true");
      list.add(""); list.add("English"); list.add("English");
      list.add("University"); list.add("Le bron"); list.add("1978-05-20");
      list.add(""); list.add(""); list.add(""); list.add("");
      list.add(""); list.add(""); list.add(""); list.add("");
      list.add("1978-05-20"); list.add("1978-05-20"); list.add("");
      list.add(""); list.add(""); list.add(""); list.add("1");
      list.add(""); list.add(""); list.add(""); list.add("");
      list.add(""); list.add(""); list.add(""); list.add("");
      list.add("1978-05-20"); list.add(""); list.add(""); list.add("");
      list.add(""); list.add(""); list.add(""); list.add("");
      list.add(""); list.add(""); list.add(""); list.add("");
      list.add(""); list.add(""); list.add(""); list.add("");
      list.add(""); list.add(""); list.add(""); list.add("");
      list.add(""); list.add(""); list.add("2"); list.add("89");
      list.add("");
      dbi.insertTemplateItems("EMPLOYEE", list);
      try {
      } catch (Exception e) {
        fail("Could not insert a field");
      }
    }
    
}
