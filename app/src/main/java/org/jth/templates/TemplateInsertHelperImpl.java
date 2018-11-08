package org.jth.templates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import org.jth.databaseHelper.DatabaseDriver;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TemplateInsertHelperImpl implements TemplateInsertHelper {

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
      Map<String, String> itemmap) throws Exception, SQLException {
    Connection conn = TemplateDriver.connectOrCreateDatabase();
    PreparedStatement stmt = null;
    String sql = "INSERT INTO TEMPLATE"
        + "(UNIQUE_IDENTIFIER_VALUE,"
        + "PROCESSING_DETAILS,"
        + "UPDATE_RECORD_ID,"
        + "UNIQUE_IDENTIFIER,"
        + "DATE_OF_BIRTH,"
        + "POSTAL_CODE_WHERE_THE_SERVICE_WAS_RECEIVED,"
        + "REGISTRATION_IN_AN_EMPLOYMENT_INTERVENTION,"
        + "A_REFERRAL_TO,"
        + "LANGUAGE_OF_SERVICE,"
        + "OFFICIAL_LANGUAGE_OF_PREFERENCE,"
        + "TYPE_OF_INSTITUTION__ORGANIZATION_WHERE_CLIENT_RECIEVED_SERVICES,"
        + "REFERRED_BY,"
        + "REFERRAL_DATE,"
        + "EMPLOYMENT_STATUS,"
        + "EDUCATION_STATUS,"
        + "OCCUPATION_IN_CANADA,"
        + "INTENDED_OCCUPATION,"
        + "INTERVENTION_TYPE,"
        + "LONG_TERM_INTERVENTION_INTERVENTION_RECIEVED,"
        + "LONG_TERM_INTERVENTION_STATUS_OF_INTERVENTION,"
        + "LONG_TERM_INTERVENTION_REASON_FOR_LEAVING_INTERVENTION,"
        + "LONG_TERM_INTERVENTION_START_DATE,"
        + "LONG_TERM_INTERVENTION_END_DATE,"
        + "LONG_TERM_INTERVENTION_SIZE_OF_EMPLOYER,"
        + "LONG_TERM_INTERVENTION_PLACEMENT_WAS,"
        + "LONG_TERM_INTERVENTION_HOURS_PER_WEEK,"
        + "LONG_TERM_INTERVENTION_CLIENT_MET_MENTOR_REGULARLY_AT,"
        + "LONG_TERM_INTERVENTION_AVERAGE_HOURS_WEEK_IN_CONTACT_WITH_MENTOR,"
        + "LONG_TERM_INTERVENTION_PROFESSION_TRADE_FOR_WHICH_SERVICES_WERE_RECIEVED,"
        + "WAS_ESSENTIAL_SKILLS_AND_APTITUDE_TRAINING_RECIEVED_AS_PART_OF_THIS_SERVICE,"
        + "COMPUTER_SKILLS,"
        + "DOCUMENT_USE,"
        + "INTERPERSONAL_SKILLS_AND_WORKPLACE_CULTURE,"
        + "LEADERSHIP_TRAINING,"
        + "NUMERACY,"
        + "SHORT_TERM_INTERVENTION_SERVICE_RECIEVED,"
        + "SHORT_TERM_INTERVENTION_DATE,"
        + "SHORT_TERM_INTERVENTION_SERVICE_RECIEVED2,"
        + "SHORT_TERM_INTERVENTION_DATE2,"
        + "SHORT_TERM_INTERVENTION_SERVICE_RECIEVED3,"
        + "SHORT_TERM_INTERVENTION_DATE3,"
        + "SHORT_TERM_INTERVENTION_SERVICE_RECIEVED4,"
        + "SHORT_TERM_INTERVENTION_DATE4,"
        + "SHORT_TERM_INTERVENTION_SERVICE_RECIEVED5,"
        + "SHORT_TERM_INTERVENTION_DATE5,"
        + "SUPPORT_SERVICES_RECIEVED,"
        + "CARE_FOR_NEWCOMER_CHILDREN,"
        + "CHILD_1_AGE,"
        + "CHILD_1_TYPE_OF_CARE,"
        + "CHILD_2_AGE,"
        + "CHILD_2_TYPE_OF_CARE,"
        + "CHILD_3_AGE,"
        + "CHILD_3_TYPE_OF_CARE,"
        + "CHILD_4_AGE,"
        + "CHILD_4_TYPE_OF_CARE,"
        + "CHILD_5_AGE,"
        + "CHILD_5_TYPE_OF_CARE,"
        + "TRANSPORTATION,"
        + "PROVISION_FOR_DISABILITIES,"
        + "TRANSLATION,"
        + "BETWEEN1,"
        + "AND1,"
        + "INTERPRETATION,"
        + "BETWEEN2,"
        + "AND2,"
        + "CRISIS_COUNSELLING,"
        + "TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENT_EMPLOYMENT_NEEDS_HOURS,"
        + "TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENT_EMPLOYMENT_NEEDS_MINUTES,"
        + "REASON_FOR_UPDATE) "
        + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    try {
      stmt = conn.prepareStatement(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    stmt.setInt(1, uniqueidentifiervalue);
    stmt.setString(2, itemmap.get("PROCESSING_DETAILS"));
    stmt.setInt(3, Integer.parseInt(itemmap.get("UPDATE_RECORD_ID")));
    stmt.setString(4, itemmap.get("UNIQUE_IDENTIFIER"));
    stmt.setDate(5, java.sql.Date.valueOf(itemmap.get("DATE_OF_BIRTH")));
    stmt.setString(6, itemmap.get("POSTAL_CODE_WHERE_THE_SERVICE_WAS_RECEIVED"));
    stmt.setBoolean(7, Boolean.parseBoolean(itemmap.get("REGISTRATION_IN_AN_EMPLOYMENT_INTERVENTION")));
    stmt.setString(8, itemmap.get("A_REFERRAL_TO"));
    stmt.setString(9, itemmap.get("LANGUAGE_OF_SERVICE"));
    stmt.setString(10, itemmap.get("OFFICIAL_LANGUAGE_OF_PREFERENCE"));
    stmt.setString(11, itemmap.get("TYPE_OF_INSTITUTION__ORGANIZATION_WHERE_CLIENT_RECIEVED_SERVICES"));
    stmt.setString(12, itemmap.get("REFERRED_BY"));
    stmt.setDate(13, java.sql.Date.valueOf(itemmap.get("REFERRAL_DATE")));
    stmt.setString(14, itemmap.get("EMPLOYMENT_STATUS"));
    stmt.setString(15, itemmap.get("EDUCATION_STATUS"));
    stmt.setString(16, itemmap.get("OCCUPATION_IN_CANADA"));
    stmt.setString(17, itemmap.get("INTENDED_OCCUPATION"));
    stmt.setString(18, itemmap.get("INTERVENTION_TYPE"));
    stmt.setString(19, itemmap.get("LONG_TERM_INTERVENTION_INTERVENTION_RECIEVED"));
    stmt.setString(20, itemmap.get("LONG_TERM_INTERVENTION_STATUS_OF_INTERVENTION"));
    stmt.setString(21, itemmap.get("LONG_TERM_INTERVENTION_REASON_FOR_LEAVING_INTERVENTION"));
    stmt.setDate(22, java.sql.Date.valueOf(itemmap.get("LONG_TERM_INTERVENTION_START_DATE")));
    stmt.setDate(23, java.sql.Date.valueOf(itemmap.get("LONG_TERM_INTERVENTION_END_DATE")));
    stmt.setString(24, itemmap.get("LONG_TERM_INTERVENTION_SIZE_OF_EMPLOYER"));
    stmt.setString(25, itemmap.get("LONG_TERM_INTERVENTION_PLACEMENT_WAS"));
    stmt.setString(26, itemmap.get("LONG_TERM_INTERVENTION_HOURS_PER_WEEK"));
    stmt.setString(27, itemmap.get("LONG_TERM_INTERVENTION_CLIENT_MET_MENTOR_REGULARLY_AT"));
    stmt.setInt(28, Integer.parseInt(itemmap.get("LONG_TERM_INTERVENTION_AVERAGE_HOURS_WEEK_IN_CONTACT_WITH_MENTOR")));
    stmt.setString(29, itemmap.get("LONG_TERM_INTERVENTION_PROFESSION_TRADE_FOR_WHICH_SERVICES_WERE_RECIEVED"));
    stmt.setBoolean(30, Boolean.parseBoolean(itemmap.get("WAS_ESSENTIAL_SKILLS_AND_APTITUDE_TRAINING_RECIEVED_AS_PART_OF_THIS_SERVICE")));
    stmt.setBoolean(31, Boolean.parseBoolean(itemmap.get("COMPUTER_SKILLS")));
    stmt.setBoolean(32, Boolean.parseBoolean(itemmap.get("DOCUMENT_USE")));
    stmt.setBoolean(33, Boolean.parseBoolean(itemmap.get("INTERPERSONAL_SKILLS_AND_WORKPLACE_CULTURE")));
    stmt.setBoolean(34, Boolean.parseBoolean(itemmap.get("LEADERSHIP_TRAINING")));
    stmt.setBoolean(35, Boolean.parseBoolean(itemmap.get("NUMERACY")));
    stmt.setString(36, itemmap.get("SHORT_TERM_INTERVENTION_SERVICE_RECIEVED"));
    stmt.setDate(37, java.sql.Date.valueOf(itemmap.get("SHORT_TERM_INTERVENTION_DATE")));
    stmt.setString(38, itemmap.get("SHORT_TERM_INTERVENTION_SERVICE_RECIEVED2"));
    stmt.setDate(39, java.sql.Date.valueOf(itemmap.get("SHORT_TERM_INTERVENTION_DATE2")));
    stmt.setString(40, itemmap.get("SHORT_TERM_INTERVENTION_SERVICE_RECIEVED3"));
    stmt.setDate(41, java.sql.Date.valueOf(itemmap.get("SHORT_TERM_INTERVENTION_DATE3")));
    stmt.setString(42, itemmap.get("SHORT_TERM_INTERVENTION_SERVICE_RECIEVED4"));
    stmt.setDate(43, java.sql.Date.valueOf(itemmap.get("SHORT_TERM_INTERVENTION_DATE4")));
    stmt.setString(44, itemmap.get("SHORT_TERM_INTERVENTION_SERVICE_RECIEVED5"));
    stmt.setDate(45, java.sql.Date.valueOf(itemmap.get("SHORT_TERM_INTERVENTION_DATE5")));
    stmt.setBoolean(46, Boolean.parseBoolean(itemmap.get("SUPPORT_SERVICES_RECIEVED")));
    stmt.setBoolean(47, Boolean.parseBoolean(itemmap.get("CARE_FOR_NEWCOMER_CHILDREN")));
    stmt.setString(48, itemmap.get("CHILD_1_AGE"));
    stmt.setString(49, itemmap.get("CHILD_1_TYPE_OF_CARE"));
    stmt.setString(50, itemmap.get("CHILD_2_AGE"));
    stmt.setString(51, itemmap.get("CHILD_2_TYPE_OF_CARE"));
    stmt.setString(52, itemmap.get("CHILD_3_AGE"));
    stmt.setString(53, itemmap.get("CHILD_3_TYPE_OF_CARE"));
    stmt.setString(54, itemmap.get("CHILD_4_AGE"));
    stmt.setString(55, itemmap.get("CHILD_4_TYPE_OF_CARE"));
    stmt.setString(56, itemmap.get("CHILD_5_AGE"));
    stmt.setString(57, itemmap.get("CHILD_5_TYPE_OF_CARE"));
    stmt.setBoolean(58, Boolean.parseBoolean(itemmap.get("TRANSPORTATION")));
    stmt.setBoolean(59, Boolean.parseBoolean(itemmap.get("PROVISION_FOR_DISABILITIES")));
    stmt.setBoolean(60, Boolean.parseBoolean(itemmap.get("TRANSLATION")));
    stmt.setString(61, itemmap.get("BETWEEN1"));
    stmt.setString(62, itemmap.get("AND1"));
    stmt.setBoolean(63, Boolean.parseBoolean(itemmap.get("INTERPRETATION")));
    stmt.setString(64, itemmap.get("BETWEEN2"));
    stmt.setString(65, itemmap.get("AND2"));
    stmt.setBoolean(66, Boolean.parseBoolean(itemmap.get("CRISIS_COUNSELLING")));
    stmt.setInt(67, Integer.parseInt(itemmap.get("TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENT_EMPLOYMENT_NEEDS_HOURS")));
    stmt.setInt(68, Integer.parseInt(itemmap.get("TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENT_EMPLOYMENT_NEEDS_MINUTES")));
    stmt.setString(69, itemmap.get("REASON_FOR_UPDATE"));
    
    try {
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return true;
  }

  //TODO TURN MAP INTO A SQL STRING
  /**
   * A funtion to see if a row exists by uniqueIdentifierValue
   * Probably move to select
  public boolean checkIfExists(Integer uniqueIdentifierValue) {
    Connection conn = TemplateDriver.connectOrCreateDatabase();
    Statement stmt = null;
    ResultSet rs = null;
    try {
      stmt = conn.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    String sql = "SELECT * FROM TEMPLATE WHERE UNIQUE_IDENTIFIER_VALUE="
        + uniqueIdentifierValue.toString();
    try {
      rs = stmt.executeQuery(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (rs.next()) {
      return true;
    } else {
      return false;
    }
  }
  */

}
