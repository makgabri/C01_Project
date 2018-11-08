package org.jth.parsing;

import java.io.*;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.text.*;
import java.util.*;
import java.lang.System;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jth.exceptions.CloseExcelFailException;
import org.jth.exceptions.NotExcelException;
import org.jth.exceptions.TemplateIndexOutOfRange;
import org.jth.exceptions.TemplateLineIndexOutOfRange;
import org.jth.templates.fieldoptions.*;


public class ParsingExcel {

    private ArrayList<ArrayList<ArrayList<String>>> templates = new ArrayList<ArrayList<ArrayList<String>>>();


    /*
    public static void main(String[] args) throws NotExcelException, IOException {
        String file =
        "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/testingTemplates/New_iCARE_Template_Comb_with_Examples.xlsx";
        //String file = "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/testingTemplates/SampleXLSFile_212kb.xls";
        ParsingExcel e = new ParsingExcel();
        System.out.println("读取xlsx格式excel结果：");
        e.getFromExcel(file);*/




        /*******************************************************************************
         * testing insertion for database.                                             *
         *******************************************************************************/
    /*
        Tuple<Integer, Map<String, ArrayList<String>>> tuple = e.insertTemplatesConverter(5);
        System.out.println(tuple.getX());
        System.out.println(tuple.getY().get("REGISTRATION_IN_AN_EMPLOYMENT_INTERVENTION"));
    }*/

    /**
     * get file type and decide which type of Excel is going to use.
     *
     * @param filename the template file path
     * @throws NotExcelException if input file is not an Excel type.
     * @throws IOException if cannot open or close a file.
     */
    public void getFromExcel(String filename) throws NotExcelException, IOException {
        InputStream is = new FileInputStream(new File(filename));
        String type = filename.substring(filename.lastIndexOf(".") + 1);
        if (type.equals("xls")) {
            Workbook wb = new HSSFWorkbook(is);
            readXls(wb);
        } else if (type.equals("xlsx")) {
            Workbook wb = new XSSFWorkbook(is);
            readXlsx(wb);
        } else {
            throw new NotExcelException();
        }
        //try {
        is.close();
            /*
        } catch (IOException e) {
            e.printStackTrace();
            throw new CloseExcelFailException();
        }*/
        //System.out.println(getSpecificTemplatesWithSpecificLine(3, 3));
        //printTemplate();
    }

    /**
     * @param wb:excel文件对象
     */
    private void readXls(Workbook wb) {
        for(int s = 2; s < wb.getNumberOfSheets(); s ++) {
            Sheet sheet = wb.getSheetAt(s);
            templates.add(new ArrayList<>());
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                boolean lineEmpty = true;
                ArrayList<String> line = new ArrayList<>();
                HSSFRow hssfrow = (HSSFRow) sheet.getRow(i);
                for (int j = 0; j <= hssfrow.getLastCellNum(); j++) {
                    HSSFCell hssfcell = hssfrow.getCell(j);
                    if (hssfcell != null) {
                        String cellValue = parseXls(hssfcell);
                        line.add(cellValue);
                    }
                }
                for (String k: line) {
                    if(!k.equals("")) {
                        lineEmpty = false;
                    }
                }
                if(!lineEmpty) {
                    templates.get(templates.size() - 1).add(line);
                }
            }
        }
    }

    /**
     * read all the data from Excel and convert into String
     * @param wb the templates that wants to read.
     */
    private void readXlsx(Workbook wb) {
        for (int s = 2; s < wb.getNumberOfSheets(); s++) {
            Sheet sheet = wb.getSheetAt(s);
            // add a new template
            templates.add(new ArrayList<ArrayList<String>>());
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                boolean lineEmpty = true;
                ArrayList<String> line = new ArrayList<>();
                XSSFRow xssfrow = (XSSFRow) sheet.getRow(i);
                for (int j = 0; j < xssfrow.getLastCellNum(); j++) {
                    XSSFCell xssfcell = xssfrow.getCell(j);
                    if(xssfcell != null) {
                        String cellValue = parseXlsx(xssfcell);
                        line.add(cellValue);
                    }
                }
                for (String k: line) {
                    if(!k.equals("")) {
                        lineEmpty = false;
                    }
                }
                if(!lineEmpty) {
                    templates.get(templates.size() - 1).add(line);
                }
            }

        }
    }

    /**
     * parsing every cell of the templates (the file is in Xlsx format).
     * @param cell cell of the templates
     * @return the every data in cell parse into String.
     */
    private String parseXlsx(XSSFCell cell) {
        String result;
        switch (cell.getCellType()) {

            case XSSFCell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    if (temp.equals("General")) {
                        format.setRoundingMode(RoundingMode.DOWN);
                    }
                    result = format.format(value);
                }
                break;
            case XSSFCell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                break;
            case XSSFCell.CELL_TYPE_BLANK:
                result = "";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }

    /**
     * parsing every cell of the templates (the file is in Xls format).
     * @param cell cell of the templates
     * @return the every data in cell parse into String.
     */
    private String parseXls(HSSFCell cell) {
        String result;
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                // progressing the
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    if (temp.equals("General")) {
                        format.setRoundingMode(RoundingMode.DOWN);
                    }
                    result = format.format(value);
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:
                result = cell.getRichStringCellValue().toString();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                result = "";
                break;
            default:
                result = "";
        }
        return result;
    }

    /**
     * print the templates
     */
    public void printTemplate() {
        for(int i = 0; i < templates.size(); i ++) {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Templates " + (i + 1));
            for(int j = 0; j < templates.get(i).size(); j ++) {
                System.out.println("*********************************************************************************");
                //System.out.println("Row Number: " + (j + 1));
                if(j == 0) {
                    System.out.println("Title : ");
                }
                for (int k = 0; k < templates.get(i).get(j).size(); k++) {
                    if(!templates.get(i).get(j).get(k).equals("")) {
                        System.out.println(templates.get(i).get(j).get(k));
                    }
                }
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }

    /**
     * get all the templates.
     * @return templates contain all 10
     */
    private ArrayList<ArrayList<ArrayList<String>>> getTemplates() {
        return templates;
    }

    /**
     * get specific template.
     * @param index the specific templates want to get.
     * @return the specific templates.
     * @throws TemplateIndexOutOfRange template index is out of range.
     */
    private ArrayList<ArrayList<String>> getSpecificTemplates(int index) throws TemplateIndexOutOfRange{
        if(index <= templates.size()){
            return templates.get(index - 1);
        } else {
            throw new TemplateIndexOutOfRange();
        }
    }

    /**
     * get the specific line in the specific template.
     * @param templateIndex index of template.
     * @param lineIndex index of line.
     * @return the line.
     * @throws TemplateLineIndexOutOfRange line index is out of range.
     * @throws TemplateIndexOutOfRange template index is out of range.
     */
    private ArrayList<String> getSpecificTemplatesWithSpecificLine(int templateIndex, int lineIndex)
            throws TemplateIndexOutOfRange, TemplateLineIndexOutOfRange {
        ArrayList<ArrayList<String>> template = getSpecificTemplates(templateIndex);
        if(lineIndex <= template.size()) {
            return template.get(lineIndex - 1);
        } else {
            throw new TemplateLineIndexOutOfRange();
        }
    }


    /*******************************************************************************
     * Methods below help insertion of template database.                          *
     *******************************************************************************/

    /**
     * parse into Fields enum class.
     * @param templateIndex get specific template.
     * @return ArrayList contain Fields enum.
     * @throws TemplateLineIndexOutOfRange line index is out of range.
     * @throws TemplateIndexOutOfRange template index is out of range.
     */
    public ArrayList<String> parsingFieldType(int templateIndex)
            throws TemplateIndexOutOfRange, TemplateLineIndexOutOfRange {
        //System.out.println(getSpecificTemplates(templateIndex).get(0).get(0));
        ArrayList<String> fieldType = getSpecificTemplatesWithSpecificLine(templateIndex, 3);
        for (int i = 0; i < fieldType.size(); i ++) {
            fieldType.set(i, checkFieldType(fieldType.get(i)));
        }

        return fieldType;
    }

    /**
     * check every String to match to Field enum
     * @param line input String
     * @return a String that proper formed.
     */
    private String checkFieldType(String line) {
        if(line.matches("^[ A-Za-z]+$")) {
            return capitalizeAndReplaceSpaceWithUnderline(line);
        } else {
            if(line.contains(":")) {
                line = line.replaceAll(":", "");
            }
            if(line.contains("?") || line.contains("/")) {
                line = line.replaceAll("\\?", "")
                        .replaceAll("/", "_");
            }
            if(line.contains("(")){
                line = line.replaceAll("\\(.*\\)", "").trim();
            }
            if(line.contains("'")) {
                line = line.replaceAll("'.", "").trim();
            }
            return capitalizeAndReplaceSpaceWithUnderline(line);
        }
    }

    /**
     * capitalize all the letter and replace the space with underline
     * @param line input String
     * @return a String that is proper formed.
     */
    private String capitalizeAndReplaceSpaceWithUnderline(String line) {
        line = line.toUpperCase();
        return line.replaceAll(" ", "_");
    }

    /**
     * get the unique identifier value in a specific template and line.
     * @param templateIndex index of a template.
     * @param lineIndex index of a line.
     * @return Integer contain unique identifier value.
     * @throws TemplateLineIndexOutOfRange line index is out of range.
     * @throws TemplateIndexOutOfRange template index is out of range.
     */
    public Integer getUniqueIdentifierValue(int templateIndex, int lineIndex)
            throws TemplateLineIndexOutOfRange, TemplateIndexOutOfRange{
        int identifierPosition = 0;
        if(lineIndex > 3) {
            for (String fields : parsingFieldType(templateIndex)) {
                if (fields.equals("UNIQUE_IDENTIFIER_VALUE")) {
                    break;
                }
                identifierPosition++;
            }
            return Integer.valueOf(
                    getSpecificTemplatesWithSpecificLine(templateIndex, lineIndex).get(identifierPosition));
        } else {
            return -1;
        }
    }
    /*
    public Tuple<Integer, ArrayList<String>> insertTemplatesConverter(int templateIndex) {
        ArrayList<String> fieldType = parsingFieldType(templateIndex);

        Tuple<Integer, ArrayList<String>> tuple = new Tuple<>();

        //HashMap<String, ArrayList<String>> itemMap = new HashMap<String, ArrayList<String>>();
        for (String fields: parsingFieldType(templateIndex)) {
            itemMap.put(fields, new ArrayList<>());
        }
        for(int i = 3; i < template.size(); i ++) {
            int j = 0;
            for (String fields: parsingFieldType(templateIndex)) {
                itemMap.get(fields).add(template.get(i).get(j));
                j ++;
            }
        }
        Integer uniqueIdentifierValue = Integer.parseInt(itemMap.get().get(0));
        return new Tuple<Integer, Map<String, ArrayList<String>>>(uniqueIdentifierValue, itemMap);
    }*/

    /**
     * convert into database friendly type.
     * @param templateIndex specific templates
     * @return a Tuple contain all information.
     */
    /*
    public Tuple<Integer, Map<String, ArrayList<String>>> insertTemplatesConverter(int templateIndex) {
        ArrayList<ArrayList<String>> template = getSpecificTemplates(templateIndex);
        HashMap<String, ArrayList<String>> itemMap = new HashMap<String, ArrayList<String>>();
        for (String fields: parsingFieldType(templateIndex)) {
            itemMap.put(fields, new ArrayList<>());
        }
        for(int i = 3; i < template.size(); i ++) {
            int j = 0;
            for (String fields: parsingFieldType(templateIndex)) {
                itemMap.get(fields).add(template.get(i).get(j));
                j ++;
            }
        }
        Integer uniqueIdentifierValue = Integer.parseInt(itemMap.get("UNIQUE_IDENTIFIER_VALUE").get(0));
        return new Tuple<Integer, Map<String, ArrayList<String>>>(uniqueIdentifierValue, itemMap);
    }*/
}


