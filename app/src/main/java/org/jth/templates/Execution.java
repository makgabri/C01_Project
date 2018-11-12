package org.jth.templates;

import java.io.IOException;
import java.sql.SQLException;
import org.jth.databaseHelper.DatabaseDriver;
import org.jth.exceptions.ConnectionFailedException;
import org.jth.exceptions.NotExcelException;
import org.jth.exceptions.TemplateIndexOutOfRange;
import org.jth.exceptions.TemplateLineIndexOutOfRange;
import org.jth.parsing.ParsingExcel;

public class Execution {

    public void execute(String filename) {
      TemplateInsertHelperImpl tih = new TemplateInsertHelperImpl();
      
      // Parsing Data
      ParsingExcel pe = new ParsingExcel();
      try {
        pe.getFromExcel(filename);
      } catch (NotExcelException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      for  (int i = 2; i < pe.getTemplatesSize(); i++) {
        // Get Template Name
        String templateName = null;
        try {
          templateName = pe.getSpecificTemplatesWithSpecificLine(i, 1).get(0);
        } catch (TemplateIndexOutOfRange | TemplateLineIndexOutOfRange e1) {
          e1.printStackTrace();
        }
        //Attempt to initialize Table in database
        try {
          TemplateDriver.initialize(DatabaseDriver.connectOrCreateDatabase(),
              templateName);
        } catch (ConnectionFailedException e) {
          e.printStackTrace();
        }
        // Insert items into table
        try {
          for (int j = 0; j < pe.getSpecificTemplates(5).size(); j++) {
            try {
              tih.insertTemplateItems(templateName,
                  pe.getSpecificTemplatesWithSpecificLine(i, j));
            } catch (SQLException e) {
              e.printStackTrace();
            } catch (TemplateIndexOutOfRange e) {
              e.printStackTrace();
            } catch (TemplateLineIndexOutOfRange e) {
              e.printStackTrace();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        } catch (TemplateIndexOutOfRange e) {
          e.printStackTrace();
        }
      }
    }
    
}
