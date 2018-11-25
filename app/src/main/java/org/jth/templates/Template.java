package org.jth.templates;

import java.util.ArrayList;

public class Template {
  
  private String templateName;
  private ArrayList<Field> fieldHolder;
  private ArrayList<String> fieldOrder;

  /**
   * An object representing a template
   * @param templateName - name of template (i.e EMPLOYEE)
   */
  public Template(String templateName) {
    this.templateName = templateName;
    this.fieldHolder = new ArrayList<Field>();
    this.fieldOrder = new ArrayList<String>();
  }
  
  /**
   * Inserts a field into fieldHolder which holds all fields of this template
   * @param field - field object to be inserted
   */
  public void insertField(Field field) {
    fieldHolder.add(field);
    fieldOrder.add(field.toString());
  }
  
  /**
   * Gets the name of this template
   * @return - the template's name
   */
  public String getName() {
    return this.templateName;
  }
  
  /**
   * gets the string representation requied for sql to create a table with the
   * fields from fieldHolder
   * @return - a string where each field's sql format is parsed and split by ","
   */
  public String toStringCreate() {
    String result = "";
    for (Field field : fieldHolder) {
      result += field.toStringCreate() + ",";
    }
    return result.substring(0, result.length() - 1);
  }
  
  /**
   * Returns the string method which is a string of all field names split by ","
   * @return - String representation of all fields in the template
   */
  public String toString() {
    String result = "";
    for (Field field : fieldHolder) {
      result += field.toString() + ",";
    }
    return result.substring(0, result.length() - 1);
  }
  
  /**
   * Gets an arrayList which contains the field type in order of fields inserted
   * @return - ArrayList of FieldTypes ordered by insertion of fields
   */
  public ArrayList<String> getFieldTypeList() {
    ArrayList<String> result = new ArrayList<String>();
    for (Field field : fieldHolder) {
      result.add(field.getType());
    }
    return result;
  }
  
  public ArrayList<Boolean> getParam() {
      ArrayList<Boolean> result = new ArrayList<>();
      for (Field field : fieldHolder) {
          result.add(field.getParam());
      }
      return result;
  }
  
  public ArrayList<String> getFieldOrder() {
    return this.fieldOrder;
  }
  
  public boolean contains(String field) {
    return this.fieldOrder.contains(field);
  }
}
