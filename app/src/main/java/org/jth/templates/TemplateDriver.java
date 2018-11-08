package org.jth.templates;

import java.sql.*;
import org.jth.exceptions.ConnectionFailedException;

public class TemplateDriver {

  // Create connection for database
  public static Connection connectOrCreateDatabase() {
    Connection connection = null;
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:template.db");
    } catch (Exception e) {
      System.out.println("Something went wrong with your connection! see below details: ");
      e.printStackTrace();
    }
    
    return connection;
  }

/**
   * This will initialize the database, or throw a ConnectionFailedException.
   * @param connection the database you'd like to write the tables to.
   * @return the connection you passed in, to allow you to continue.
   * @throws ConnectionFailedException If the tables couldn't be initialized, throw
   */
  public static Connection initialize(Connection connection) throws ConnectionFailedException {
    if (!initializeDatabase(connection)) {
      throw new ConnectionFailedException();
    }
    return connection;
  }
  
  public static Connection clear(Connection connection) throws ConnectionFailedException {
    if (!clearDatabase(connection)) {
      throw new ConnectionFailedException();
    }
    return connection;
  }
  
  private static boolean initializeDatabase(Connection connection) {
    Statement statement = null;
    
    try {
      statement = connection.createStatement();
      
      // Create table called template and set its fields
      String sql = "CREATE TABLE TEMPLATE " 
          + "(UNIQUE_IDENTIFIER_VALUE INTEGER PRIMARY KEY NOT NULL UNIQUE,"
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
          + "SHORT_TERM_INTERVENTION_SERVICE_RECIEVED2 LONGVARCHAR,"
          + "SHORT_TERM_INTERVENTION_DATE2 DATE,"
          + "SHORT_TERM_INTERVENTION_SERVICE_RECIEVED3 LONGVARCHAR,"
          + "SHORT_TERM_INTERVENTION_DATE3 DATE,"
          + "SHORT_TERM_INTERVENTION_SERVICE_RECIEVED4 LONGVARCHAR,"
          + "SHORT_TERM_INTERVENTION_DATE4 DATE,"
          + "SHORT_TERM_INTERVENTION_SERVICE_RECIEVED5 LONGVARCHAR,"
          + "SHORT_TERM_INTERVENTION_DATE5 DATE,"
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
          + "REASON_FOR_UPDATE LONGVARCHAR)";
      statement.executeUpdate(sql);
      
      statement.close();
      return true;
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
  
  private static boolean clearDatabase(Connection connection) {
        Statement statement = null;
        
        try {
          statement = connection.createStatement();
          
          
          String sql = "DROP TABLE TEMPLATE;";
          statement.executeUpdate(sql);
          
          statement.close();
          return true;
          
        } catch (Exception e) {
          e.printStackTrace();
        }
        return false;
      }

  public static boolean doesTableExists(Connection connection) throws SQLException {
    DatabaseMetaData dbm = connection.getMetaData();
    ResultSet tables = dbm.getTables(null, null, "TEMPLATE", null);
    if (tables.next()) {
      return true;
    } else {
      return false;
    }
  }
  
}
