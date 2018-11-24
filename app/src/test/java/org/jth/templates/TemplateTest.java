package org.jth.templates;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TemplateTest {
  
  @Test
  void testFieldName() {
    Field field = new Field("UNIQUE_IDENTIFIER_VALUE",
        "INTEGER", true);
    assertEquals(field.toString(), "UNIQUE_IDENTIFIER_VALUE");
  }
  
  @Test
  void testFieldType() {
    Field field = new Field("UNIQUE_IDENTIFIER_VALUE",
        "INTEGER", true);
    assertEquals(field.getType(), "INTEGER");
  }
  
  @Test
  void testFieldToStringCreate() {
    Field field = new Field("UNIQUE_IDENTIFIER_VALUE",
        "INTEGER", true);
    assertEquals(field.toStringCreate(),
        "UNIQUE_IDENTIFIER_VALUE INTEGER NOT NULL");
  }
  
  @Test
  void testFieldToStringCreatePlain() {
    Field field = new Field("A_REFERRAL_TO",
        "LONGVARCHAR", false);
    assertEquals(field.toStringCreate(), "A_REFERRAL_TO LONGVARCHAR");
  }
  
  @Test
  void testInitializeTemplate() {
    Template template = new Template("EMPLOYEE");
    assertEquals(template.getName(), "EMPLOYEE");
  }
  
  @Test
  void testInsertOneFieldTemplate() {
    Template template = new Template("EMPLOYEE");
    template.insertField(new Field("UNIQUE_IDENTIFIER_VALUE", "INTEGER", true));
    assertEquals(template.toString(), "UNIQUE_IDENTIFIER_VALUE");
    assertEquals(template.toStringCreate(),
        "UNIQUE_IDENTIFIER_VALUE INTEGER NOT NULL");
  }
  
  @Test
  void testInsertTwoFieldTemplate() {
    Template template = new Template("EMPLOYEE");
    template.insertField(new Field("UNIQUE_IDENTIFIER_VALUE", "INTEGER", true));
    template.insertField(new Field("UPDATE_RECORD_ID", "INTEGER", false));
    assertEquals(template.toString(),
        "UNIQUE_IDENTIFIER_VALUE,UPDATE_RECORD_ID");
    assertEquals(template.toStringCreate(),
        "UNIQUE_IDENTIFIER_VALUE INTEGER NOT NULL,"
        + "UPDATE_RECORD_ID INTEGER");
  }

}
