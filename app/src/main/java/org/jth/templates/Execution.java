package org.jth.templates;

import org.jth.databaseHelper.DatabaseDriver;
import org.jth.exceptions.ConnectionFailedException;

public class Execution {

    /** TODO: Run the parser and template driver together*/
    public void execute() {
      // Replace dummyTemplateName with actual template name by parser
      String dummyTemplateName = "EMPLOYEE";
      TemplateInsertHelperImpl tih = new TemplateInsertHelperImpl();
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
        tih.insertTemplateItems(dummyTemplateName,
            getSpecificTemplatesWithSpecificLine(dummyTemplateNum, i))
      }
    }
    
}
