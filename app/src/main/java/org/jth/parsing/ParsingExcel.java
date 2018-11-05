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
import org.jth.templates.fieldoptions.*;


public class ParsingExcel {

    private ArrayList<ArrayList<ArrayList<String>>> templates = new ArrayList<ArrayList<ArrayList<String>>>();
    private static Fields fields;
    private static InstitutionType institutionType;
    private static MandatoryFields mandatoryFields;
    private static ReferredOptions referredOptions;
    private static UniqueIdentifiers uniqueIdentifiers;



    public static void main(String[] args) throws NotExcelException, IOException {
        /*
        String file = "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/testingTemplates/New_iCARE_Template_Comb_with_Examples.xlsx";
        //String file = "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/testingTemplates/SampleXLSFile_212kb.xls";
        ParsingExcel e = new ParsingExcel();
        System.out.println("读取xlsx格式excel结果：");
        e.getFromExcel(file);*/
        ParsingExcel e = new ParsingExcel();
        String line = e.removeBrackets("Referral Date (YYYY-MM-DD)");
        System.out.println(line);
        System.out.println(Fields.valueOf(e.capitalizeAndReplaceSpaceWithUnderline(line)));
        e.checkFieldType("Referral Date");
        System.out.println(Fields.valueOf(e.checkFieldType("D:ate of/Bir:th (YYYY-MM-DD)?")));

    }

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
        printTemplate();
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
    private void printTemplate() {
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
    public ArrayList<ArrayList<ArrayList<String>>> getTemplates() {
        return templates;
    }

    /**
     * get specific template.
     * @param index the specific templates want to get.
     * @return the specific templates.
     */
    public ArrayList<ArrayList<String>> getSpecificTemplates(int index) {
        if(index <= templates.size()){
            return templates.get(index - 1);
        } else {
            System.out.println("Index is out of range!");
            return null;
        }
    }

    /**
     * get the specific line in the specific template.
     * @param templateIndex index of template.
     * @param lineIndex index of line.
     * @return the line.
     */
    public ArrayList<String> getSpecificTemplatesWithSpecificLine(int templateIndex, int lineIndex) {
        if(templateIndex <= templates.size()) {
            if(lineIndex <= templates.get(templateIndex - 1).size()) {
                return templates.get(templateIndex - 1).get(lineIndex - 1);
            } else {
                System.out.println("Line Index is out of range!");
                return null;
            }
        } else {
            System.out.println("Template index is out of range!");
            return null;
        }
    }

    public void parsingFieldType(int templateIndex) {
        ArrayList<String> fieldType = getSpecificTemplatesWithSpecificLine(templateIndex, 3);
        for (int i = 0; i < fieldType.size(); i ++) {
            checkFieldType(fieldType.get(i));
        }
    }

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

    private String removeBrackets(String line) {
        return line.replaceAll("\\(.*\\)", "").trim();
    }

    private String removeQuestionMarkSlash(String line) {
        return line.replaceAll("\\?", "")
                .replaceAll("/", "");
    }

    private String capitalizeAndReplaceSpaceWithUnderline(String line) {
        line = line.toUpperCase();
        return line.replaceAll(" ", "_");
    }
}


