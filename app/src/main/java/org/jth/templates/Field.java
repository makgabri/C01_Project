package org.jth.templates;

public class Field {
  
  private String fieldName;
  private String fieldType;
  private Boolean key;
  private Boolean notEmpty;
  private Boolean unique;

  /**
   * An object representing a field
   * @param fieldName - field name of field
   * @param fieldType - field type to be distinguished
   * @param key - whether this field is the key of the table
   * @param notEmpty - whether cannot be empty
   * @param unique - whether this field is unique for each record
   */
  public Field(String fieldName, String fieldType, Boolean key, Boolean notEmpty,
                Boolean unique) {
    this.fieldName = fieldName;
    this.fieldType = fieldType;
    this.key = key;
    this.notEmpty = notEmpty;
    this.unique = unique;
  }
  
  /**
   * string representation for sql to create a column for this field
   * @return - string for sql to be parsed
   */
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
  
  /**
   * String representation of this field is the field name
   * @return - field name
   */
  public String toString() {
    return this.fieldName;
  }
  
  /**
   *  gets the type of this field defined at first
   * @return - field type by string 
   */
  public String getType() {
    return this.fieldType;
  }
}
