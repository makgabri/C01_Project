package org.jth.templates;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.jth.exceptions.ConnectionFailedException;

public class TemplateDriver {

  // Create connection for database
  public static Connection connectOrCreateDatabase() {
    Connection connection = null;
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:app.db");
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
          + "(UNIQUEIV INTEGER PRIMARY KEY NOT NULL UNIQUE,"
          + "PROCESSING_DETAIL TEXT"
          + "UPDATE_RECORD_ID INTEGER"
          + "UNIQUE_IDENTIFIER UNIQUEIDENTIFIERS NOT NULL,"
          + "DATE_OF_BIRTH DATE NOT NULL,"
          + "POSTAL_CODE TEXT NOT NULL,"
          + "REGISTRATION_IN_EMPLOYMENT_INTERVENTION BOOLEAN NOT NULL,"
          + "A_REFERRAL_TO TEXT,"
          + "LANGUAGE_OF_SERVICE LANGUAGE NOT NULL,"
          + "OFFICIAL_LANGUAGE_OF_PREFERENCE LANGUAGE NOT NULL,"
          + "TYPE_OF_INSTITUTION_WHERE_CLIENT_RECIEVED_SERVICE INSTITUTIONTYPE NOT NULL,"
          + "REFERRED_BY REFFERREDOPTIONS NOT NULL,"
          + "REFERRAL_DATE DATE,"
          + "EMPLOYMENT_STATUS TEXT,"
          + "EDUCATION_STATUS TEXT,"
          + "OCCUPATION_IN_CANADA TEXT,"
          + "INTENDED_OCCUPATION TEXT,"
          + "INTERVENTION_TYPE TEXT,"
          + "LONGTERM_INTERVENTION_RECIEVED TEXT,"
          + "LONGTERM_INTERVENTION_STATUS TEXT,"
          + "LONGTERM_INTERVENTION_REASON_LEAVING TEXT,"
          + "LONGTERM_INTERVENTION_START_DATE DATE,"
          + "LONGTERM_INTERVENTION_END_DATE DATE,"
          + "LONGTERM_INTERVENTION_SIZE_OF_EMPLOYER TEXT,"
          + "LONGTERM_INTERVENTION_PLACEMENT_WAS TEXT,"
          + "LONGTERM_INTERVENTION_HOURS_PER_WEEK TEXT,"
          + "LONGTERM_INTERVENTION_CLIENT_MET_MENTOR_REGULARLY TEXT,"
          + "LONGTERM_INTERVENTION_AVG_HOURS_PER_WEEK_SERVICE_RECIEVED INTEGER,"
          + "LONGTERM_INTERVENTION_PROFESSION_FOR_WHICH_SERVICE_WERE_RECIEVED TEXT,"
          + "WAS_ESSENTIAL_SKILLS_AND_APTITUDE_TRAINING_RECIEVED BOOLEAN,"
          + "COMPUTER_SKILLS BOOLEAN,"
          + "DOCUMENT_USE BOOLEAN,"
          + "INTERPERSONAL_SKILLS_AND_WORKPLACE_CULTURE BOOLEAN,"
          + "LEADERSHIP_TRAINING BOOLEAN,"
          + "NUMERACY BOOLEAN,"
          + "SHORTTERM_INTERVENTION_SERVICE_RECIEVED TEXT,"
          + "SHORTTERM_INTERVENTION_DATE DATE,"
          + "SHORTTERM_INTERVENTION_SERVICE_RECIEVED TEXT,"
          + "SHORTTERM_INTERVENTION_DATE DATE,"
          + "SHORTTERM_INTERVENTION_SERVICE_RECIEVED TEXT,"
          + "SHORTTERM_INTERVENTION_DATE DATE,"
          + "SHORTTERM_INTERVENTION_SERVICE_RECIEVED TEXT,"
          + "SHORTTERM_INTERVENTION_DATE DATE,"
          + "SHORTTERM_INTERVENTION_SERVICE_RECIEVED TEXT,"
          + "SHORTTERM_INTERVENTION_DATE DATE,"
          + "SUPPORT_SERVICES_RECIEVED BOOLEAN,"
          + "CARE_FOR_NEWCOMER_CHILDREN BOOLEAN,"
          + "CHILD1_AGE TEXT,"
          + "CHILD1_TYPE_OF_CARE TEXT,"
          + "CHILD2_AGE TEXT,"
          + "CHILD2_TYPE_OF_CARE TEXT,"
          + "CHILD3_AGE TEXT,"
          + "CHILD3_TYPE_OF_CARE TEXT,"
          + "CHILD4_AGE TEXT,"
          + "CHILD4_TYPE_OF_CARE TEXT,"
          + "CHILD5_AGE TEXT,"
          + "CHILD5_TYPE_OF_CARE TEXT,"
          + "TRANSPORTATION BOOLEAN,"
          + "PROVISION_FOR_DISABILITIES BOOLEAN,"
          + "TRANSLATION BOOLEAN,"
          + "BETWEEN LANGUAGE,"
          + "AND LANGUAGE,"
          + "INTERPRETATION BOOLEAN,"
          + "BETWEEN LANGUAGE,"
          + "AND LANGUAGE,"
          + "CRISIS_COUNSELLING BOOLEAN,"
          + "TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENTS_EMPLOYMENT_NEEDS_HOURS INTEGER,"
          + "TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENTS_EMPLOYMENT_NEEDS_MINUTES INTEGER,"
          + "REASON_FOR_UPDATE TEXT,";
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

}
