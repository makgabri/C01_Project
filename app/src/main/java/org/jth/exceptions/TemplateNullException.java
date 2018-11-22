package org.jth.exceptions;

public class TemplateNullException extends Exception {
    public TemplateNullException() {
        super("Template is NULL! Please set up the template first.");
    }

}
