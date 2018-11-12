package org.jth.templates;

import java.sql.SQLException;
import org.jth.databaseHelper.DatabaseDriver;
import org.jth.exceptions.ConnectionFailedException;
import org.jth.exceptions.TemplateIndexOutOfRange;
import org.jth.exceptions.TemplateLineIndexOutOfRange;
import org.jth.parsing.ParsingExcel;

public class Execution {

    /** TODO: Run the parser and template driver together*/
    public void execute() {
      // Replace dummyTemplateName with actual template name by parser
      String dummyTemplateName = "EMPLOYEE";
      TemplateInsertHelperImpl tih = new TemplateInsertHelperImpl();
      ParsingExcel pe = new ParsingExcel();
      try {
        TemplateDriver.initialize(DatabaseDriver.connectOrCreateDatabase(),
            dummyTemplateName);
      } catch (ConnectionFailedException e) {
        e.printStackTrace();
      }
      
      // Replace dummyLinesize with actualy number of lines
      int dummyLineSize = 1;
      int dummyTemplateNum = 7;
      for (int i = 0; i < dummyLineSize; i++) {
        try {
          tih.insertTemplateItems(dummyTemplateName,
              pe.getSpecificTemplatesWithSpecificLine(dummyTemplateNum, i));
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
    }
    
}
