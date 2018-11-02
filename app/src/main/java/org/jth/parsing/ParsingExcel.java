package org.jth.parsing;

import java.io.*;
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


public class ParsingExcel {

    private ArrayList<ArrayList<ArrayList<String>>> templates = new ArrayList<ArrayList<ArrayList<String>>>();

    /*
    public static void main(String[] args) throws CloseExcelFailException, NotExcelException, IOException {
        //String file = "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/testingTemplates/New_iCARE_Template_Comb_with_Examples.xlsx";
        String file = "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/testingTemplates/SampleXLSFile_212kb.xls";
        ParsingExcel e = new ParsingExcel();
        System.out.println("读取xlsx格式excel结果：");
        e.getFromExcel(file);
    }*/

    /**
     * get file type and decide which type of Excel is going to use.
     *
     * @param filename the template file path
     */
    public void getFromExcel(String filename) throws CloseExcelFailException, NotExcelException, IOException {
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
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CloseExcelFailException();
        }
        //printTemplate();
    }

    /**
     * @param wb:excel文件对象
     */
    private void readXls(Workbook wb) {
        for(int s = 0; s < wb.getNumberOfSheets(); s ++) {
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
        for (int s = 0; s < wb.getNumberOfSheets(); s++) {
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
                System.out.println("Row Number: " + (j + 1));
                if(j == 0) {
                    System.out.println("Title : ");
                }
                for (int k = 0; k < templates.get(i).get(j).size(); k++) {
                    if(!templates.get(i).get(j).get(k).equals("")) {
                        System.out.println("Column Number: " + (k + 1) + " " + templates.get(i).get(j).get(k));
                    }
                }
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }

    /**
     * get templates.
     * @return templates contain all 10
     */
    public ArrayList<ArrayList<ArrayList<String>>> getTemplates() {
        return templates;
    }
}

