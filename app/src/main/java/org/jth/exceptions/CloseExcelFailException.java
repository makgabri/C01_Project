package org.jth.exceptions;

public class CloseExcelFailException extends Exception{
    public CloseExcelFailException() {
        super("unable to close Excel");
    }
}
