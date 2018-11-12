package org.jth.parsing;

import org.jth.exceptions.NotExcelException;
import org.jth.exceptions.TemplateIndexOutOfRange;
import org.jth.exceptions.TemplateLineIndexOutOfRange;
import org.jth.templates.fieldoptions.Fields;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParsingTest {

    private static ParsingExcel parsingExcel = new ParsingExcel();
    private static final String
            xlsxPath = "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/Team1/app/src/test/java/org/jth/parsing/New_iCARE_Template_Comb_with_Examples.xlsx";
    private static final String
            xlsPath = "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/Team1/app/src/test/java/org/jth/parsing/SampleXLSFile_212kb.xls";

    private void readFromExcel() throws IOException, NotExcelException {
        parsingExcel.getFromExcel(xlsxPath);
    }

    @Test
    @DisplayName("Not a Excel File.")
    public void testReadNonExcelFile() {
        String nonExcelFilePath = "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/Team1/app/src/test/java/org/jth/parsing/NotExcel.txt";
        assertThrows(NotExcelException.class, () -> {
            parsingExcel.getFromExcel(nonExcelFilePath);
        }, "exception was thrown for an Non Excel file");
    }

    @Test
    @DisplayName("Get a field type from a specific template.")
    public void testGetFieldType() {
        ArrayList<String> field;
        try {
            readFromExcel();
            field = parsingExcel.parsingFieldType(5);
            for (String s : field) {
                Fields.valueOf(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("test if parsing read all templates")
    public void testReadAllTemplates() {
        try {
            assertEquals(8, parsingExcel.getTemplatesSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Get a cell in a specific line from a specific template")
    public void testGetCellValue() {
        try {
            ArrayList<String> line = parsingExcel.getSpecificTemplatesWithSpecificLine(1, 4);
            assertEquals("1978-05-20", line.get(3));
            assertEquals("902-628-1285", line.get(4));
            assertEquals("hnestor@cathcrosscultural.org", line.get(6));
            assertEquals("E - East", line.get(10));
            assertEquals("Yes", line.get(16));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @DisplayName("Template index out of bound")
    public void testTemplateIndexOutOfBound() {
        assertThrows(TemplateIndexOutOfRange.class, () -> {
            parsingExcel.getSpecificTemplatesWithSpecificLine(10000, 4);
        }, "Template Index out of bound");
    }

    @Test
    @DisplayName("Template index out of bound")
    public void testLineIndexOutOfBound() {
        assertThrows(TemplateLineIndexOutOfRange.class, ()->{
            parsingExcel.getSpecificTemplatesWithSpecificLine(4, 100000);
        }, "Line Index out of bound");
    }

    @Test
    @DisplayName("test read xls file")
    public void testReadXlsFile() {
        try {
            parsingExcel.getFromExcel(xlsPath);
            assertEquals(1000, parsingExcel.getSpecificTemplates(9).size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}