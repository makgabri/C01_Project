package org.jth.exceptions;

public class NotExcelException extends Exception {
    public NotExcelException() {
        super("Input file is not an Excel File!");
    }
}
