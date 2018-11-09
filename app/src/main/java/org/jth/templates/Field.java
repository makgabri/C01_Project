package org.jth.templates;

public class Field {
  
  private String fieldName;
  private String fieldType;
  private Boolean key;
  private Boolean notEmpty;
  private Boolean unique;

  public Field(String fieldName, String fieldType, Boolean key, Boolean notEmpty,
                Boolean unique) {
    this.fieldName = fieldName;
    this.fieldType = fieldType;
    this.key = key;
    this.notEmpty = notEmpty;
    this.unique = unique;
  }
  
  public String toStringCreate() {
    String result = this.fieldName + " " + this.fieldType;
    if (this.key) {
      result += " PRIMARY KEY";
    }
    if (this.notEmpty) {
      result += " NOT NULL";
    }
    if (this.unique) { 
      result += " UNIQUE";
    }
    return result;
  }
  
  public String toStringInsert() {
    return this.fieldName;
  }
  
  public String getType() {
    return this.fieldType;
  }
}
