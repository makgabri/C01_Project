package org.jth.exceptions;

@SuppressWarnings("serial")
public class IncorrectDataFormatException extends Exception {
  
    public IncorrectDataFormatException() {}
    
    public IncorrectDataFormatException(String templateType, int row, int col)
    {
      super("There is an error in " + templateType + " in"
          + System.lineSeparator() + "Row: " + Integer.toString(row)
          + System.lineSeparator() + "Column: " + Integer.toString(col));
    }
}
