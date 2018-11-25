package org.jth.templates;

import java.io.File;
import java.io.IOException;
import org.jth.exceptions.NotExcelException;
import org.jth.exceptions.TemplateIndexOutOfRange;
import org.jth.exceptions.TemplateLineIndexOutOfRange;
import org.jth.exceptions.TemplateNullException;
import org.jth.parsing.ParsingExcel;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TemplateFormatTest {
    ParsingExcel pe = ParsingExcel.getInstance();
    TemplateFormat tf = TemplateFormat.getInstance();

    @Test
    @DisplayName("Initializing FieldList")
    public void testFieldList() {
      String path = new File("test/java/org/jth/templates/New_iCARE_Template_Comb_with_Examples.xlsx").getAbsolutePath();
      if (!tf.doesTemplateExist("EMPLOYMENT_RELATED_SERVICES")) {
        try {
          pe.getFromExcel(path);
        } catch (NotExcelException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        } catch (TemplateNullException e) {
          e.printStackTrace();
        }
        try {
          tf.insertTemplate("EMPLOYMENT_RELATED_SERVICES", pe.parsingFieldType(5));
        } catch (TemplateIndexOutOfRange | TemplateLineIndexOutOfRange e) {
          e.printStackTrace();
        } catch (TemplateNullException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
}
