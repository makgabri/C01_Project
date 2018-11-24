package org.jth.templates;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.jth.exceptions.*;
import org.jth.parsing.ParsingExcel;

public class Execution {

    public void execute(Connection connection, String filename) {
      TemplateInsertHelperImpl tih = new TemplateInsertHelperImpl();
      TemplateFormat tf = TemplateFormat.getInstance();  
      ParsingExcel pe = ParsingExcel.getInstance();

      try {
        pe.getFromExcel(filename);
      } catch (NotExcelException | IOException | TemplateNullException e) {
        e.printStackTrace();
      }

      try {
          for (int i = 1; i <= pe.getTemplatesSize(); i++) {
              // Get Template Name
              String templateType = null;
              try {
                  templateType = pe.parsingTitle(i);
              } catch (TemplateIndexOutOfRange | TemplateLineIndexOutOfRange e1) {
                  e1.printStackTrace();
              }
              // Initialize template as an object first
              // Comment out below for debuging message
              // System.out.println("TEMPLATE TITLE:" + templateType);
              if (!tf.doesTemplateExist(templateType)) {
                  try {
                      tf.insertTemplate(templateType, pe.parsingFieldType(i));
                  } catch (TemplateIndexOutOfRange | TemplateLineIndexOutOfRange e1) {
                      e1.printStackTrace();
                  }
              }
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
                                  pe.getSpecificTemplatesWithSpecificLine(i, j), j);
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
      } catch (TemplateNullException e){
          e.printStackTrace();
        }
    }
    
}
