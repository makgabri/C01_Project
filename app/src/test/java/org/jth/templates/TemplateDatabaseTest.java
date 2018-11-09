package org.jth.templates;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.HashMap;
import org.jth.exceptions.ConnectionFailedException;
import org.jth.templates.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TemplateDatabaseTest {
    TemplateInsertHelperImpl dbi = new TemplateInsertHelperImpl();
    
    @BeforeEach
    public void setUp() throws SQLException {
      if (!TemplateDriver.doesTableExists(TemplateDriver.connectOrCreateDatabase())) {
        try {
          TemplateDriver.initialize(TemplateDriver.connectOrCreateDatabase());
        } catch (Exception e) {
          fail("Should not throw any exception");
        }
      }
    }
 
    @AfterEach
    public void cleanUp() {
      try {
        TemplateDriver.clear(TemplateDriver.connectOrCreateDatabase());
      } catch (Exception e) {
        fail("Should not throw any exception");
      }
    }
    
    @Test
    void testInsertUniqueIV() throws Exception {
      HashMap<String, String> map = new HashMap<>();
      map.put("PROCESSING_DETAILS", null);
      map.put("UPDATE_RECORD_ID", "123");
      map.put("UNIQUE_IDENTIFIER", "FOSS/GCMS Client ID");
      map.put("DATE_OF_BIRTH", "1978-05-20");
      map.put("POSTAL_CODE_WHERE_THE_SERVICE_WAS_RECEIVED", "A1A 2B2");
      map.put("REGISTRATION_IN_AN_EMPLOYMENT_INTERVENTION", "true");
      map.put("A_REFERRAL_TO", null);
      map.put("LANGUAGE_OF_SERVICE", "English");
      map.put("OFFICIAL_LANGUAGE_OF_PREFERENCE", "English");
      map.put("TYPE_OF_INSTITUTION__ORGANIZATION_WHERE_CLIENT_RECIEVED_SERVICES", "University");
      map.put("REFERRED_BY", "Le Bron");
      map.put("REFERRAL_DATE", "1978-05-20");
      map.put("EMPLOYMENT_STATUS", null);
      map.put("EDUCATION_STATUS", null);
      map.put("OCCUPATION_IN_CANADA", null);
      map.put("INTENDED_OCCUPATION", null);
      map.put("INTERVENTION_TYPE", null);
      map.put("LONG_TERM_INTERVENTION_INTERVENTION_RECIEVED", null);
      map.put("LONG_TERM_INTERVENTION_STATUS_OF_INTERVENTION", null);
      map.put("LONG_TERM_INTERVENTION_REASON_FOR_LEAVING_INTERVENTION", null);
      map.put("LONG_TERM_INTERVENTION_START_DATE", "1978-05-20");
      map.put("LONG_TERM_INTERVENTION_END_DATE", "1978-05-20");
      map.put("LONG_TERM_INTERVENTION_SIZE_OF_EMPLOYER", null);
      map.put("LONG_TERM_INTERVENTION_PLACEMENT_WAS", null);
      map.put("LONG_TERM_INTERVENTION_HOURS_PER_WEEK", null);
      map.put("LONG_TERM_INTERVENTION_CLIENT_MET_MENTOR_REGULARLY_AT", null);
      map.put("LONG_TERM_INTERVENTION_AVERAGE_HOURS_WEEK_IN_CONTACT_WITH_MENTOR", "1");
      map.put("LONG_TERM_INTERVENTION_PROFESSION_TRADE_FOR_WHICH_SERVICES_WERE_RECIEVED", null);
      map.put("WAS_ESSENTIAL_SKILLS_AND_APTITUDE_TRAINING_RECIEVED_AS_PART_OF_THIS_SERVICE", null);
      map.put("COMPUTER_SKILLS", null);
      map.put("DOCUMENT_USE", null);
      map.put("INTERPERSONAL_SKILLS_AND_WORKPLACE_CULTURE", null);
      map.put("LEADERSHIP_TRAINING", null);
      map.put("NUMERACY", null);
      map.put("SHORT_TERM_INTERVENTION_SERVICE_RECIEVED", null);
      map.put("SHORT_TERM_INTERVENTION_DATE", "1978-05-20");
      map.put("SHORT_TERM_INTERVENTION_SERVICE_RECIEVED2", null);
      map.put("SHORT_TERM_INTERVENTION_DATE2", "1978-05-20");
      map.put("SHORT_TERM_INTERVENTION_SERVICE_RECIEVED3", null);
      map.put("SHORT_TERM_INTERVENTION_DATE3", "1978-05-20");
      map.put("SHORT_TERM_INTERVENTION_SERVICE_RECIEVED4", null);
      map.put("SHORT_TERM_INTERVENTION_DATE4", "1978-05-20");
      map.put("SHORT_TERM_INTERVENTION_SERVICE_RECIEVED5", null);
      map.put("SHORT_TERM_INTERVENTION_DATE5", "1978-05-20");
      map.put("SUPPORT_SERVICES_RECIEVED", null);
      map.put("CARE_FOR_NEWCOMER_CHILDREN", null);
      map.put("CHILD_1_AGE", null);
      map.put("CHILD_1_TYPE_OF_CARE", null);
      map.put("CHILD_2_AGE", null);
      map.put("CHILD_2_TYPE_OF_CARE", null);
      map.put("CHILD_3_AGE", null);
      map.put("CHILD_3_TYPE_OF_CARE", null);
      map.put("CHILD_4_AGE", null);
      map.put("CHILD_4_TYPE_OF_CARE", null);
      map.put("CHILD_5_AGE", null);
      map.put("CHILD_5_TYPE_OF_CARE", null);
      map.put("TRANSPORTATION", null);
      map.put("PROVISION_FOR_DISABILITIES", null);
      map.put("TRANSLATION", null);
      map.put("BETWEEN1", null);
      map.put("AND1", null);
      map.put("INTERPRETATION", null);
      map.put("BETWEEN2", null);
      map.put("AND2", null);
      map.put("CRISIS_COUNSELLING", null);
      map.put("TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENT_EMPLOYMENT_NEEDS_HOURS", "2");
      map.put("TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENT_EMPLOYMENT_NEEDS_MINUTES", "89");
      map.put("REASON_FOR_UPDATE LONGVARCHAR", null);
      dbi.insertTemplateItems(12345678, map);
      try {
      } catch (Exception e) {
        fail("Could not insert a unique IV");
      }
    }
    
}
