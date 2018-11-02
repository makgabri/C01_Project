package org.jth.templates;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.jth.databaseHelper.DatabaseDriver;

public class EmploymentTemplateImpl implements EmploymentTemplateHelper {

  @Override
  public int insertUser(String uniqueIV) {
    Connection conn = DatabaseDriver.connectOrCreateDatabase();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int roleId = -1;
    
    try {
        String sql = "INSERT INTO UNIQUE IDENTIFIER VALUE(UIV) VALUES (?);";
        stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, uniqueIV.toUpperCase());
        
        int id = stmt.executeUpdate();
        
        if (id > 0) {
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                roleId = rs.getInt(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
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
    
    return roleId;
  }

  @Override
  public Map<String, String> insertData(String processing_detail, Integer email,
      String unique_identifier, Date dateofbirth, String postalcode,
      Boolean registration_in_employment_intervention, String a_referrral_to,
      Languages language_of_service, Languages official_language_pref,
      String type_of_institution_where_client_recieved_service,
      String reffered_by, Date referral_date, String employment_status,
      String education_status, String occupation_in_canada,
      String intended_occupation, String intervention_type,
      String longtermintervention_recieved, String longtermintervention_status,
      String longtermintervention_reason_leaving,
      Date longtermintervention_start_date, Date longtermintervention_end_date,
      String longtermintervention_size_of_employer,
      String longterminvervention_placement_was,
      String longtermintervention_hours_per_week,
      String longtermintervention_client_met_mentor_regularly_at,
      Integer longtermintervention_avg_hours_per_week_in_contact_with_mentor,
      String longtermintervention_profession_which_service_was_recieved,
      Boolean essentialskill_and_aptitudetraining_recieved_as_part_of_service,
      Boolean computer_skills, Boolean document_use,
      Boolean interpersonal_skills_and_workplace_culture,
      Boolean leadership_training, Boolean numeracy,
      String shorttermintervention_service_required,
      Date shorttermintervention_date,
      String shorttermintervention_service_required2,
      Date shorttermintervention_date2,
      String shorttermintervention_service_required3,
      Date shorttermintervention_date3,
      String shorttermintervention_service_required4,
      Date shorttermintervention_date4,
      String shorttermintervention_service_required5,
      Date shorttermintervention_date5, Boolean support_services_recieved,
      Boolean care_for_newcomer_children, String child1_age,
      String child1_type_of_care, String child2_age, String child2_type_of_care,
      String child3_age, String child3_type_of_care, String child4_age,
      String child4_type_of_care, String child5_age, String child5_type_of_care,
      Boolean transportation, Boolean provisions_for_disabilities,
      Boolean translation, Languages between1, Languages and1,
      Boolean interpretation, Languages between2, Languages and2,
      Boolean crisis_counselling,
      Integer time_spent_with_client_addressing_employment_needs_hours,
      Integer time_spent_with_client_addressing_employment_needs_minutess,
      String reason_for_update) {
    // TODO Auto-generated method stub
    return null;
  }

}
