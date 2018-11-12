package org.jth.templates;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.HashMap;
import org.jth.exceptions.ConnectionFailedException;
import org.jth.templates.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TemplateTest {
  
  @Test
  void testFieldName() {
    Field field = new Field("UNIQUE_IDENTIFIER_VALUE",
        "INTEGER", true, true, true);
    assertEquals(field.toString(), "UNIQUE_IDENTIFIER_VALUE");
  }
  
  @Test
  void testFieldType() {
    Field field = new Field("UNIQUE_IDENTIFIER_VALUE",
        "INTEGER", true, true, true);
    assertEquals(field.getType(), "INTEGER");
  }
  
  @Test
  void testFieldToStringCreate() {
    Field field = new Field("UNIQUE_IDENTIFIER_VALUE",
        "INTEGER", true, true, true);
    assertEquals(field.toStringCreate(),
        "UNIQUE_IDENTIFIER_VALUE INTEGER PRIMARY KEY NOT NULL UNIQUE");
  }
  
  @Test
  void testFieldToStringCreatePlain() {
    Field field = new Field("A_REFERRAL_TO",
        "LONGVARCHAR", false, false, false);
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
    template.insertField(new Field("UNIQUE_IDENTIFIER_VALUE", "INTEGER", true,
        true, true));
    assertEquals(template.toString(), "UNIQUE_IDENTIFIER_VALUE");
    assertEquals(template.toStringCreate(),
        "UNIQUE_IDENTIFIER_VALUE INTEGER PRIMARY KEY NOT NULL UNIQUE");
  }
  
  @Test
  void testInsertTwoFieldTemplate() {
    Template template = new Template("EMPLOYEE");
    template.insertField(new Field("UNIQUE_IDENTIFIER_VALUE", "INTEGER", true,
        true, true));
    template.insertField(new Field("UPDATE_RECORD_ID", "INTEGER", false,
        false, false));
    assertEquals(template.toString(),
        "UNIQUE_IDENTIFIER_VALUE,UPDATE_RECORD_ID");
    assertEquals(template.toStringCreate(),
        "UNIQUE_IDENTIFIER_VALUE INTEGER PRIMARY KEY NOT NULL UNIQUE,"
        + "UPDATE_RECORD_ID INTEGER");
  }
  
  @Test
  void testEmployeeTemplate() {
    Template result = new Template("EMPLOYEE");
    result.insertField(new Field("UNIQUE_IDENTIFIER_VALUE", "INTEGER", true, true, true));
    result.insertField(new Field("PROCESSING_DETAILS", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("UPDATE_RECORD_ID", "INTEGER", false, false, false));
    result.insertField(new Field("UNIQUE_IDENTIFIER", "LONGVARCHAR", false, true, false));
    result.insertField(new Field("DATE_OF_BIRTH", "DATE", false, true, false));
    result.insertField(new Field("POSTAL_CODE_WHERE_THE_SERVICE_WAS_RECEIVED",
        "LONGVARCHAR", false, true, false));
    result.insertField(new Field("REGISTRATION_IN_AN_EMPLOYMENT_INTERVENTION", "BOOLEAN", false, true, false));
    result.insertField(new Field("A_REFERRAL_TO", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LANGUAGE_OF_SERVICE", "LONGVARCHAR", false, true, false));
    result.insertField(new Field("OFFICIAL_LANGUAGE_OF_PREFERENCE", "LONGVARCHAR", false, true, false));
    result.insertField(new Field("TYPE_OF_INSTITUTION__ORGANIZATION_WHERE_CLIENT_RECIEVED_SERVICES",
        "LONGVARCHAR", false, true, false));
    result.insertField(new Field("REFERRED_BY", "LONGVARCHAR", false, true, false));
    result.insertField(new Field("REFERRAL_DATE", "DATE", false, false, false));
    result.insertField(new Field("EMPLOYMENT_STATUS", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("EDUCATION_STATUS", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("OCCUPATION_IN_CANADA", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("INTENDED_OCCUPATION", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("INTERVENTION_TYPE", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_INTERVENTION_RECIEVED",
        "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_STATUS_OF_INTERVENTION",
        "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_REASON_FOR_LEAVING_INTERVENTION",
        "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_START_DATE", "DATE", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_END_DATE", "DATE", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_SIZE_OF_EMPLOYER", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_PLACEMENT_WAS", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_HOURS_PER_WEEK", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_CLIENT_MET_MENTOR_REGULARLY_AT",
        "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_AVERAGE_HOURS_WEEK_IN_CONTACT_WITH_MENTOR",
        "INTEGER", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_PROFESSION_TRADE_FOR_WHICH_SERVICES_WERE_RECIEVED",
        "LONGVARCHAR", false, false, false));
    result.insertField(new Field("WAS_ESSENTIAL_SKILLS_AND_APTITUDE_TRAINING_RECIEVED_AS_PART_OF_THIS_SERVICE",
        "BOOLEAN", false, false, false));
    result.insertField(new Field("COMPUTER_SKILLS", "BOOLEAN", false, false, false));
    result.insertField(new Field("DOCUMENT_USE", "BOOLEAN", false, false, false));
    result.insertField(new Field("INTERPERSONAL_SKILLS_AND_WORKPLACE_CULTURE", "BOOLEAN", false, false, false));
    result.insertField(new Field("LEADERSHIP_TRAINING", "BOOLEAN", false, false, false));
    result.insertField(new Field("NUMERACY", "BOOLEAN", false, false, false));
    result.insertField(new Field("SHORT_TERM_INTERVENTION_SERVICE_RECIEVED", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("SHORT_TERM_INTERVENTION_DATE", "DATE", false, false, false));
    result.insertField(new Field("SUPPORT_SERVICES_RECIEVED", "BOOLEAN", false, false, false));
    result.insertField(new Field("CARE_FOR_NEWCOMER_CHILDREN", "BOOLEAN", false, false, false));
    result.insertField(new Field("CHILD_1_AGE", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("CHILD_1_TYPE_OF_CARE", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("CHILD_2_AGE", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("CHILD_2_TYPE_OF_CARE", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("CHILD_3_AGE", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("CHILD_3_TYPE_OF_CARE", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("CHILD_4_AGE", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("CHILD_4_TYPE_OF_CARE", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("CHILD_5_AGE", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("CHILD_5_TYPE_OF_CARE", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("TRANSPORTATION", "BOOLEAN", false, false, false));
    result.insertField(new Field("PROVISION_FOR_DISABILITIES", "BOOLEAN", false, false, false));
    result.insertField(new Field("TRANSLATION", "BOOLEAN", false, false, false));
    result.insertField(new Field("BETWEEN1", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("AND1", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("INTERPRETATION", "BOOLEAN", false, false, false));
    result.insertField(new Field("BETWEEN2", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("AND2", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("CRISIS_COUNSELLING", "BOOLEAN", false, false, false));
    result.insertField(new Field("TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENT_EMPLOYMENT_NEEDS_HOURS",
        "INTEGER", false, false, false));
    result.insertField(new Field("TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENT_EMPLOYMENT_NEEDS_MINUTES",
        "INTEGER", false, false, false));
    result.insertField(new Field("REASON_FOR_UPDATE", "LONGVARCHAR", false, false, false));
    assertEquals(result.toStringCreate(),
            "UNIQUE_IDENTIFIER_VALUE INTEGER PRIMARY KEY NOT NULL UNIQUE,"
            + "PROCESSING_DETAILS LONGVARCHAR,"
            + "UPDATE_RECORD_ID INTEGER,"
            + "UNIQUE_IDENTIFIER LONGVARCHAR NOT NULL,"
            + "DATE_OF_BIRTH DATE NOT NULL,"
            + "POSTAL_CODE_WHERE_THE_SERVICE_WAS_RECEIVED LONGVARCHAR NOT NULL,"
            + "REGISTRATION_IN_AN_EMPLOYMENT_INTERVENTION BOOLEAN NOT NULL,"
            + "A_REFERRAL_TO LONGVARCHAR,"
            + "LANGUAGE_OF_SERVICE LONGVARCHAR NOT NULL,"
            + "OFFICIAL_LANGUAGE_OF_PREFERENCE LONGVARCHAR NOT NULL,"
            + "TYPE_OF_INSTITUTION__ORGANIZATION_WHERE_CLIENT_RECIEVED_SERVICES LONGVARCHAR NOT NULL,"
            + "REFERRED_BY LONGVARCHAR NOT NULL,"
            + "REFERRAL_DATE DATE,"
            + "EMPLOYMENT_STATUS LONGVARCHAR,"
            + "EDUCATION_STATUS LONGVARCHAR,"
            + "OCCUPATION_IN_CANADA LONGVARCHAR,"
            + "INTENDED_OCCUPATION LONGVARCHAR,"
            + "INTERVENTION_TYPE LONGVARCHAR,"
            + "LONG_TERM_INTERVENTION_INTERVENTION_RECIEVED LONGVARCHAR,"
            + "LONG_TERM_INTERVENTION_STATUS_OF_INTERVENTION LONGVARCHAR,"
            + "LONG_TERM_INTERVENTION_REASON_FOR_LEAVING_INTERVENTION LONGVARCHAR,"
            + "LONG_TERM_INTERVENTION_START_DATE DATE,"
            + "LONG_TERM_INTERVENTION_END_DATE DATE,"
            + "LONG_TERM_INTERVENTION_SIZE_OF_EMPLOYER LONGVARCHAR,"
            + "LONG_TERM_INTERVENTION_PLACEMENT_WAS LONGVARCHAR,"
            + "LONG_TERM_INTERVENTION_HOURS_PER_WEEK LONGVARCHAR,"
            + "LONG_TERM_INTERVENTION_CLIENT_MET_MENTOR_REGULARLY_AT LONGVARCHAR,"
            + "LONG_TERM_INTERVENTION_AVERAGE_HOURS_WEEK_IN_CONTACT_WITH_MENTOR INTEGER,"
            + "LONG_TERM_INTERVENTION_PROFESSION_TRADE_FOR_WHICH_SERVICES_WERE_RECIEVED LONGVARCHAR,"
            + "WAS_ESSENTIAL_SKILLS_AND_APTITUDE_TRAINING_RECIEVED_AS_PART_OF_THIS_SERVICE BOOLEAN,"
            + "COMPUTER_SKILLS BOOLEAN,"
            + "DOCUMENT_USE BOOLEAN,"
            + "INTERPERSONAL_SKILLS_AND_WORKPLACE_CULTURE BOOLEAN,"
            + "LEADERSHIP_TRAINING BOOLEAN,"
            + "NUMERACY BOOLEAN,"
            + "SHORT_TERM_INTERVENTION_SERVICE_RECIEVED LONGVARCHAR,"
            + "SHORT_TERM_INTERVENTION_DATE DATE,"
            + "SUPPORT_SERVICES_RECIEVED BOOLEAN,"
            + "CARE_FOR_NEWCOMER_CHILDREN BOOLEAN,"
            + "CHILD_1_AGE LONGVARCHAR,"
            + "CHILD_1_TYPE_OF_CARE LONGVARCHAR,"
            + "CHILD_2_AGE LONGVARCHAR,"
            + "CHILD_2_TYPE_OF_CARE LONGVARCHAR,"
            + "CHILD_3_AGE LONGVARCHAR,"
            + "CHILD_3_TYPE_OF_CARE LONGVARCHAR,"
            + "CHILD_4_AGE LONGVARCHAR,"
            + "CHILD_4_TYPE_OF_CARE LONGVARCHAR,"
            + "CHILD_5_AGE LONGVARCHAR,"
            + "CHILD_5_TYPE_OF_CARE LONGVARCHAR,"
            + "TRANSPORTATION BOOLEAN,"
            + "PROVISION_FOR_DISABILITIES BOOLEAN,"
            + "TRANSLATION BOOLEAN,"
            + "BETWEEN1 LONGVARCHAR,"
            + "AND1 LONGVARCHAR,"
            + "INTERPRETATION BOOLEAN,"
            + "BETWEEN2 LONGVARCHAR,"
            + "AND2 LONGVARCHAR,"
            + "CRISIS_COUNSELLING BOOLEAN,"
            + "TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENT_EMPLOYMENT_NEEDS_HOURS INTEGER,"
            + "TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENT_EMPLOYMENT_NEEDS_MINUTES INTEGER,"
            + "REASON_FOR_UPDATE LONGVARCHAR");
  }
}
