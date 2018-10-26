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
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ParsingExcel {
    public static void main(String[] args) {
//        String xls="/Users/zhu/Desktop/untitled1/src/hello.xls";
        String xlsx="/Users/zhu/Desktop/untitled1/src/New_iCARE_Template_Comb_with_Examples1.xlsx";
        ParsingExcel e=new ParsingExcel();
        //       System.out.println("读取xls格式excel结果：");
        //      e.getFromExcel(xls);
        System.out.println("读取xlsx格式excel结果：");
        e.getFromExcel(xlsx);
    }

    public void getFromExcel(String filename){
        InputStream is=null;
        Workbook wb=null;
        String type=filename.substring(filename.lastIndexOf(".")+1);//获取文件类型
        File file=new File(filename);
        try {
            is=new FileInputStream(file);
            if(type.equals("xls")){
                wb=new HSSFWorkbook(is);
                readXls(wb);
            }else if(type.equals("xlsx")){
                wb=new XSSFWorkbook(is);
                readXlsx(wb);
                //readXlsx_type(wb);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally{
            try {
                is.close();
//                wb.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param wb:excel文件对象
     */
    public void readXls(Workbook wb){

        Sheet sheet=wb.getSheetAt(0);//对应excel正文对
        for(int i=0;i<=sheet.getLastRowNum();i++){
            HSSFRow hssfrow=(HSSFRow) sheet.getRow(i);//行
            for (int j = 0; j <=hssfrow.getLastCellNum(); j++) {
                HSSFCell hssfcell=hssfrow.getCell(j);
                if(hssfcell!=null){
                    hssfcell.getCellType();
                    hssfcell.setCellType(Cell.CELL_TYPE_STRING);//设置单元格类型为String类型，以便读取时候以string类型，也可其它
                    String cellValue=hssfcell.getStringCellValue();
                    System.out.print(cellValue);
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    /**
     *
     * @param wb:excel文件对象
     */
    public void readXlsx(Workbook wb){
        int k= wb.getNumberOfSheets();
        System.out.print("Number of sheet:"+wb.getNumberOfSheets());
        for(int s= 0; s< 1; s++) {
            System.out.print("sheet:" + s + "\t");

            Sheet sheet = wb.getSheetAt(s);
            System.out.print("SheetNum:");
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                XSSFRow xssfrow = (XSSFRow) sheet.getRow(i);
                for (int j = 0; j < xssfrow.getLastCellNum(); j++) {
                    XSSFCell xssfcell = xssfrow.getCell(j);
                    if (xssfcell != null) {
                        xssfcell.setCellType(Cell.CELL_TYPE_STRING);
                        String cellValue = xssfcell.getStringCellValue();
                        System.out.print(cellValue);
                    }
                    System.out.print("\t");
                }
                System.out.println();
            }
        }
    }

    /**
     *
     * @param wb:excel文件对象
     */
    public void readXlsx_type(Workbook wb){
        int k= wb.getNumberOfSheets();
        System.out.print("Number of sheet:"+wb.getNumberOfSheets()+"\t");
        for(int s= 2; s< 3; s++) {
            System.out.print("sheet:" + s + "\t");

            Sheet sheet = wb.getSheetAt(s);
            System.out.print("SheetNum:");
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                XSSFRow xssfrow = (XSSFRow) sheet.getRow(i);
                for (int j = 0; j < xssfrow.getLastCellNum(); j++) {
                    XSSFCell xssfcell = xssfrow.getCell(j);
                    if (xssfcell != null) {
                        //xssfcell.setCellType(Cell.CELL_TYPE_STRING);
                        //String cellValue = xssfcell.getStringCellValue();
                        String cellValue = parseExcel(xssfcell);
                        System.out.print(cellValue);
                    }
                    System.out.print("\t");
                }
                System.out.println();
            }
        }
    }

    private String parseExcel(XSSFCell cell) {
        String result = new String();
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                            .getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
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
                result = "";
            default:
                result = "";
                break;
        }
        return result;
    }



}

