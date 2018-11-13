package org.jth.templates;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class TemplateFormat {
  
  /**
   * templateMap is a Hashmap of all possible templates with the
   *    template name as the key and the template object as the value
   * fieldMap is a HashMap of all possible fields predefined in file fieldformat
   * initialized states whether fieldMap has been initialized
   */
  public static HashMap<String, Template> templateMap = new HashMap<>();
  public static HashMap<String, Field> fieldMap = new HashMap<>();
  public static boolean initialized = false;;
  
  public TemplateFormat() {
    if (!initialized) {
      Properties props = new Properties();
      InputStream is = TemplateFormat.class.getResourceAsStream("fieldformat");
      try {
        props.load(is);
      } catch (IOException e) {
        e.printStackTrace();
      }
      Field temp;
      for (Object keys : props.keySet()) {
        String[] param = props.getProperty((String) keys).split(";");
        temp = new Field((String) keys, param[0], Boolean.valueOf(param[1]),
            Boolean.valueOf(param[2]), Boolean.valueOf(param[3]));
        fieldMap.put((String) keys, temp);
      }
      initialized = true;
    }
  }
  
  public boolean doesTemplateExist(String templateType) {
    return (templateMap.get(templateType) != null);
  }
  
  public void insertTemplate(String templateType, ArrayList<String> fieldList) {
   Template template = new Template(templateType);
   for (String field : fieldList) {
     template.insertField(fieldMap.get(field));
   }
  }
  
  /**
   * gets the template object of a certain template
   * @param templateType - String of the template name
   * @return - the template object of the template wanted
   */
  public static Template getTemplate(String templateType) {
    return templateMap.get(templateType);
  }
  
  /**
   * Function creates a template for Employee
   * @return - the template object for Employee 
   */
  public static Template Employee() {
    Template result = new Template("EMPLOYMENT_RELATED_SERVICES");
    result.insertField(new Field("PROCESSING_DETAILS", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("UPDATE_RECORD_ID", "INTEGER", false, false, false));
    result.insertField(new Field("UNIQUE_IDENTIFIER", "LONGVARCHAR", false, true, false));
    result.insertField(new Field("UNIQUE_IDENTIFIER_VALUE", "INTEGER", true, true, true));
    result.insertField(new Field("DATE_OF_BIRTH", "DATE", false, true, false));
    result.insertField(new Field("POSTAL_CODE_WHERE_THE_SERVICE_WAS_RECEIVED", "LONGVARCHAR", false, true, false));
    result.insertField(new Field("REGISTRATION_IN_AN_EMPLOYMENT_INTERVENTION", "BOOLEAN", false, true, false));
    result.insertField(new Field("A_REFERRAL_TO", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LANGUAGE_OF_SERVICE", "LONGVARCHAR", false, true, false));
    result.insertField(new Field("OFFICIAL_LANGUAGE_OF_PREFERENCE", "LONGVARCHAR", false, true, false));
    result.insertField(new Field("TYPE_OF_INSTITUTION__ORGANIZATION_WHERE_CLIENT_RECIEVED_SERVICES", "LONGVARCHAR", false, true, false));
    result.insertField(new Field("REFERRED_BY", "LONGVARCHAR", false, true, false));
    result.insertField(new Field("REFERRAL_DATE", "DATE", false, false, false));
    result.insertField(new Field("EMPLOYMENT_STATUS", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("EDUCATION_STATUS", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("OCCUPATION_IN_CANADA", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("INTENDED_OCCUPATION", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("INTERVENTION_TYPE", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_INTERVENTION_RECIEVED", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_STATUS_OF_INTERVENTION", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_REASON_FOR_LEAVING_INTERVENTION", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_START_DATE", "DATE", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_END_DATE", "DATE", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_SIZE_OF_EMPLOYER", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_PLACEMENT_WAS", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_HOURS_PER_WEEK", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_CLIENT_MET_MENTOR_REGULARLY_AT", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_AVERAGE_HOURS_WEEK_IN_CONTACT_WITH_MENTOR", "INTEGER", false, false, false));
    result.insertField(new Field("LONG_TERM_INTERVENTION_PROFESSION_TRADE_FOR_WHICH_SERVICES_WERE_RECIEVED", "LONGVARCHAR", false, false, false));
    result.insertField(new Field("WAS_ESSENTIAL_SKILLS_AND_APTITUDE_TRAINING_RECIEVED_AS_PART_OF_THIS_SERVICE", "BOOLEAN", false, false, false));
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
    result.insertField(new Field("TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENT_EMPLOYMENT_NEEDS_HOURS", "INTEGER", false, false, false));
    result.insertField(new Field("TIME_SPENT_WITH_CLIENT_ADDRESSING_CLIENT_EMPLOYMENT_NEEDS_MINUTES",  "INTEGER", false, false, false));
    result.insertField(new Field("REASON_FOR_UPDATE", "LONGVARCHAR", false, false, false));
    return result;
  }

}
