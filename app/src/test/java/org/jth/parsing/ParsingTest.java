package org.jth.parsing;

import org.jth.exceptions.NotExcelException;
import org.jth.templates.fieldoptions.Fields;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParsingTest {

    private static ParsingExcel parsingExcel = new ParsingExcel();
    private static final String xlsxPath =  "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/Team1/app/src/test/java/org/jth/parsing/New_iCARE_Template_Comb_with_Examples.xlsx";
    private static final String xlsPath =  "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/Team1/app/src/test/java/org/jth/parsing/SampleXLSFile_212kb.xls";

    private void readFromExcel() throws IOException, NotExcelException {
        parsingExcel.getFromExcel(xlsxPath);
    }

    @Test
    @DisplayName("Not a Excel File.")
    public void testReadNonExcelFile() {
        String nonExcelFilePath = "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/Team1/app/src/test/java/org/jth/parsing/NotExcel.txt";
        assertThrows(NotExcelException.class, ()->{
            parsingExcel.getFromExcel(nonExcelFilePath);
        }, "exception was thrown for an Non Excel file");
    }

    @Test
    @DisplayName("Get a unique identifier value from a specific line")
    public void testGetUniqueIdentifierValue() {
        try {
            readFromExcel();
            assertEquals(12345678,
                    parsingExcel.getUniqueIdentifierValue(5, 4).intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Get a field type from a specific template.")
    public void testGetFieldType() {
        ArrayList<String> field;
        try {
            readFromExcel();
            field = parsingExcel.parsingFieldType(5);
            for (String s: field) {
                Fields.valueOf(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}