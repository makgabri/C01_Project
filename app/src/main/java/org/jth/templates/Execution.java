package org.jth.templates;

import java.io.IOException;
import java.sql.Connection;
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
      TemplateFormat tf = new TemplateFormat();  
      ParsingExcel pe = new ParsingExcel();
      Connection connection = null;

      try {
        pe.getFromExcel(filename);
      } catch (NotExcelException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      connection = DatabaseDriver.connectOrCreateDatabase();
      
      for  (int i = 2; i < pe.getTemplatesSize(); i++) {
        // Get Template Name
        String templateType = null;
        try {
          templateType = pe.parsingTitle(i);
        } catch (TemplateIndexOutOfRange | TemplateLineIndexOutOfRange e1) {
          e1.printStackTrace();
        }
        // Initialize template as an object first
        System.out.println("TEMPLATE TITLE:" + templateType);
        if (!tf.doesTemplateExist(templateType)) {
          try {
            tf.insertTemplate(templateType, pe.parsingFieldType(i));
          } catch (TemplateIndexOutOfRange | TemplateLineIndexOutOfRange e1) {
            e1.printStackTrace();
          }
        }
        System.out.println("Past initializing template object");
        //Attempt to initialize Table in database
        try {
          TemplateDriver.initialize(connection, templateType);
        } catch (ConnectionFailedException e) {
          e.printStackTrace();
        }
        // Insert items into table
        try {
          for (int j = 4; j <= pe.getSpecificTemplates(i).size(); j++) {
            try {
              tih.insertTemplateItems(connection, templateType,
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
