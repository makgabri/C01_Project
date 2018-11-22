package org.jth.templates;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

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
  
  // Initializes template format if it has not been initialized
  // During initialization, class scans through fieldformat to initialize field
  // objetcs
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
  
  /**
   * Creates a template object with information about the template and inserts
   * it into the template Map
   * @param templateType - name of template
   * @param fieldList - list of all fields in the template
   */
  public void insertTemplate(String templateType, ArrayList<String> fieldList) {
    // Initialize template object
   Template template = new Template(templateType);
   // Search through fieldList and compare with existing fields to get field object
   for (String field : fieldList) {
     if (template.getFieldOrder().contains(field)) {
       // Perform actions if field exists to prevent sql error
       String[] temp = fieldMap.get(field).getParam().split(";");
       int counter = 2;
       while (template.getFieldOrder().contains(field + counter)) {
         counter += 1;
       }
       template.insertField(new Field(field+counter,
           fieldMap.get(field).getType(), Boolean.valueOf(temp[0]),
           Boolean.valueOf(temp[1]), Boolean.valueOf(temp[2]))); 
     } else if (field.equals("BETWEEN") || field.equals("AND")) {
       // Perform necessary actions if field is BETWEEN or AND cause it causes
       // sql errors
       String[] param = fieldMap.get(field).getParam().split(";");
       int counter = 1;
       while (template.getFieldOrder().contains(field + counter)) {
         counter += 1;
       }
       template.insertField(new Field(field+counter,
           fieldMap.get(field).getType(), Boolean.valueOf(param[0]),
           Boolean.valueOf(param[1]), Boolean.valueOf(param[2])));
     } else {
       template.insertField(fieldMap.get(field));
     }
   }
   templateMap.put(templateType, template);
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
   * Gets the list of all templates names as a Set
   * @return - set of template names
   */
  public static Set<String> getTemplateList() {
    return templateMap.keySet();
  }

  /**
   * gets string array of all fields defined
   * @return - String array of all fields
   */
  public static String[] getFieldList() {
    return fieldMap.keySet().toArray(new String[fieldMap.size()]);
  }
}
