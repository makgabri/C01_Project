package org.jth.templates;

import org.jth.databaseHelper.DatabaseDriver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.util.ArrayList;

public class TemplateSelectHelperImplTest {
    TemplateSelectHelperImpl tsh = new TemplateSelectHelperImpl();
    TemplateFormat tf = new TemplateFormat();
    Connection conn = DatabaseDriver.connectOrCreateDatabase();
    
    @Test
    @DisplayName("Getting a value")
    public void testGetValue() {
      String value = tsh.getValueFromField(conn, 1235, "CLIENT_PROFILE",
          "STREET_NUMBER");
      assertEquals(value, "32");
    }
    
    @Test
    @DisplayName("Getting a total of yes in email")
    public void testGetEmailTotal() {
      int value = tsh.getTotalFromField(conn, "CLIENT_PROFILE",
          "DOES_THE_CLIENT_HAVE_AN_EMAIL_ADDRESS", "true");
      assertEquals(value, 6);
    }
    
    @Test
    @DisplayName("Get Unique Iv")
    public void testGetUniqueIV() {
      ArrayList<String> temp = tsh.getUniqueIV(conn, "CLIENT_PROFILE",
          "OFFICIAL_LANGUAGE_OF_PREFERENCE", "English");
      assertEquals(temp.size(), 4);
    }

}
