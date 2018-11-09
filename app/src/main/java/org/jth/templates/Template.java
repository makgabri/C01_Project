package org.jth.templates;

import java.util.ArrayList;

public class Template {
  
  private String templateName;
  private ArrayList<Field> fieldHolder;

  public Template(String templateName) {
    this.templateName = templateName;
    this.fieldHolder = new ArrayList<Field>();
  }
  
  public void insertField(Field field) {
    fieldHolder.add(field);
  }
  
  public String getName() {
    return this.templateName;
  }
  
  public String toStringCreate() {
    String result = "";
    for (Field field : fieldHolder) {
      result += field.toStringCreate() + ",";
    }
    return result.substring(0, result.length() - 1);
  }
  
  public String toStringInsert() {
    String result = "";
    for (Field field : fieldHolder) {
      result += field.toStringInsert() + ",";
    }
    return result.substring(0, result.length() - 1);
  }
  
  public ArrayList<String> getFieldTypeList() {
    ArrayList<String> result = new ArrayList<String>();
    for (Field field : fieldHolder) {
      result.add(field.getType());
    }
    return result;
  }
}
