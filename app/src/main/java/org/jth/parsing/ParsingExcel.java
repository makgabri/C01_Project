package org.jth.parsing;

import jxl.*;
import org.apache.poi.*;

import java.io.*;
import java.text.*;
import java.util.*;
import java.lang.System;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jth.exceptions.CloseExcelFailException;
import org.jth.exceptions.ParsingExcelVersionFailException;


public class ParsingExcel {

    // template[0] will be the title of templates
    private ArrayList<ArrayList<String>> template = new ArrayList<ArrayList<String>>();

    public static void main(String[] args) throws ParsingExcelVersionFailException, CloseExcelFailException {
        String xlsx = "/Users/xingyuanzhu/Documents/UofT/CSCC01/pro/New_iCARE_Template_Comb_with_Examples.xlsx";
        ParsingExcel e = new ParsingExcel();
        System.out.println("读取xlsx格式excel结果：");
        e.getFromExcel(xlsx);
    }

    /**
     * get file type and decide which type of Excel is going to use.
     *
     * @param filename the template file path
     * @throws ParsingExcelVersionFailException - if parsing Excel type fail throw it.
     */
    public void getFromExcel(String filename) throws ParsingExcelVersionFailException, CloseExcelFailException {
        InputStream is = null;
        Workbook wb = null;
        String type = filename.substring(filename.lastIndexOf(".") + 1);
        File file = new File(filename);
        try {
            is = new FileInputStream(file);
            if (type.equals("xls")) {
                wb = new HSSFWorkbook(is);
                readXls(wb);
            } else if (type.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
                readXlsx(wb);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ParsingExcelVersionFailException();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CloseExcelFailException();
        }
        printTemplate();
    }

    /**
     * @param wb:excel文件对象
     */
    private void readXls(Workbook wb) {
        boolean firstTime = true;
        Sheet sheet = wb.getSheetAt(0);//对应excel正文对
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            HSSFRow hssfrow = (HSSFRow) sheet.getRow(i);//行
            for (int j = 0; j <= hssfrow.getLastCellNum(); j++) {
                HSSFCell hssfcell = hssfrow.getCell(j);
                if (hssfcell != null) {
                    hssfcell.getCellType();
                    hssfcell.setCellType(Cell.CELL_TYPE_STRING);
                    String cellValue = hssfcell.getStringCellValue();
                    if(cellValue != null) {
                        System.out.print(cellValue);
                    }
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    /**
     * read all the data from Excel and convert into String
     * @param wb the templates that wants to read.
     */
    private void readXlsx(Workbook wb) {
        int k = wb.getNumberOfSheets();
        System.out.print("Number of sheet: " + k);
        for (int s = 1; s < 2; s++) {
            System.out.print("sheet: " + s + "\t");
            Sheet sheet = wb.getSheetAt(s);
            //System.out.print("SheetTitle: ");
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {

                template.add(new ArrayList<String>());
                XSSFRow xssfrow = (XSSFRow) sheet.getRow(i);

                for (int j = 0; j < xssfrow.getLastCellNum(); j++) {
                    XSSFCell xssfcell = xssfrow.getCell(j);
                    if(xssfcell != null) {
                        String cellValue = parseExcel(xssfcell);
                        template.get(i).add(cellValue);
                        /*
                        if (xssfcell.getCellType() != Cell.CELL_TYPE_BLANK) {
                            if(firstTime) {
                                template.add(new ArrayList<String>());
                                firstTime = false;
                            }
                            template.get(template.size() - 1).add(cellValue);
                        }*/
                    }
                }
            }
        }
    }

    /**
     * parsing every cell of the templates.
     * @param cell cell of the templates
     * @return the every data in cell parse into String. If it is a blank cell, return -1.
     */
    private String parseExcel(XSSFCell cell) {
        String result = new String();
        switch (cell.getCellType()) {

            case HSSFCell.CELL_TYPE_NUMERIC:
                // progressing the 
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
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
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    result = format.format(value);
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
            default:
                result = "";
                break;
        }
        return result;
    }

    /*
    private void printTemplate() {
        //System.out.println("****************************************************************************");
        for(int i = 0; i < template.size(); i ++) {
            //if(template.get(i).equals(""))
            for(int j = 0; j < template.get(i).size(); j ++) {
                if(template.get(i).get(j).length() != 0) {
                    System.out.print(template.get(i).get(j) + "          ");
                }
            }
            System.out.println();
            System.out.println("*************************************************************************");
        }
        System.out.println("****************************************************************************");
    }*/
}

