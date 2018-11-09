package org.jth.cmdTerminal;
//Parsing imports
import org.jth.parsing.*;
import java.util.Scanner;
import org.jth.exceptions.*;

import java.io.IOException;
// Database neccesity imports
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Target;
import java.security.NoSuchAlgorithmException;
// Database neccesity imports
import org.jth.databaseHelper.*;
// User neccessity imports
import java.util.HashMap;
import java.util.Map;


public class cmdTerminal{
    // Global variable to store recent credential
    private String username;
    private String password;
    // Global variable to store parsed excel file
    private ParsingExcel parsed = new ParsingExcel();
    private DatabaseInsertHelper dbi = new DatabaseInsertHelperImpl();
	private DatabaseUpdateHelper dbu = new DatabaseUpdateHelperImpl();
    private DatabaseSelectHelper dbs = new DatabaseSelectHelperImpl();
    
    // Function for logging in
    private void logIn()
    {
        System.out.println("Enter username");
        Scanner scanner = new Scanner(System.in);
        username = scanner.nextLine();
        System.out.println("Enter password");
        password = scanner.nextLine();
    }

    // Function for logging out
    private void logOut()
    {
        username = null;
        password = null;
    }

    // Function for parsing an excel file
    private void parseFromFile()
    {
        // Get excel file name
        System.out.println("Enter file name");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        // Parse the excel file
        try{
            parsed.getFromExcel(filename);
        }
        // Error catching
        catch (NotExcelException e)
        {
            System.out.println("Not an excel file. Unable to open");
        }
        catch (IOException e)
        {
            System.out.println("Something happened when opening file. Please restart the program and try again");
        }
    }

    // Function for calling to print the excel file

    private void printRecentExcel()
    {
        parsed.printTemplate();
    }
    
    //Menu method
    private void printCmdMenu()
    {
        System.out.println("-----------------------------------------------------------");
        System.out.println("Welcome to the management system");
        System.out.println("Enter 0 to exit");
        System.out.println("Enter 1 to log in");
        System.out.println("Enter 2 to log out");
        System.out.println("Enter 3 to parse a file");
        System.out.println("Enter 4 to print out the recent parsed file");
        System.out.print("Your choice of command is : ");
    }

    // Main method
    public static void main(String[] args)
    {
        System.out.println("Welcome to the management system");
        // Integer correspond to command menu
        int cmdNum = 1;
        // Instance of cmd terminal
        cmdTerminal terminalInstance = new cmdTerminal();
        while(cmdNum != 0)
        {
            terminalInstance.printCmdMenu();
            Scanner nextCommand = new Scanner(System.in);
            cmdNum = nextCommand.nextInt();
            System.out.println("-----------------------------------------------------------");
            switch (cmdNum) {
                case 1:  terminalInstance.logIn();;
                         break;
                case 2:  terminalInstance.logOut();;
                         break;
                case 3:  terminalInstance.parseFromFile();;
                         break;
                case 4:  terminalInstance.printRecentExcel();;
                         break;
            }
        }
        System.out.println("Thank you for using the system ");
    }

}