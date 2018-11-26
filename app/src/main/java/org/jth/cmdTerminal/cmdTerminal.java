package org.jth.cmdTerminal;
//Parsing imports
import org.jth.parsing.*;

import java.sql.Connection;
import java.util.Scanner;
import org.jth.exceptions.*;

import java.io.IOException;
// Database neccesity imports
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.jth.GUI.Windows.StarterWindow;
// Database neccesity imports
import org.jth.databaseHelper.*;
// Login neccessity imports
import org.jth.security.*;
import org.jth.user.Roles;


public class cmdTerminal{
    // Global variable to store recent credential
    private String usermail;
    private String password;
    // Global variable to store parsed excel file
    private ParsingExcel parsed = ParsingExcel.getInstance();
    private DatabaseInsertHelper dbi = new DatabaseInsertHelperImpl();
	@SuppressWarnings("unused")
    private DatabaseUpdateHelper dbu = new DatabaseUpdateHelperImpl();
    private DatabaseSelectHelper dbs = new DatabaseSelectHelperImpl();
    private AuthenticateImpl auth;
    
    private void initialize(Connection connection) throws ConnectionFailedException {

        DatabaseDriver.initialize(connection);
        DatabaseInsertHelper dbInsert = new DatabaseInsertHelperImpl();
        int orgRoleId = dbInsert.insertRole(Roles.ORGANIZATION.name());
        if (orgRoleId < 0) {
            System.out.println("Failed to insert ORGANIZATION role");
        }
        int teqRoleId = dbInsert.insertRole(Roles.TEQ.name());
        if (teqRoleId < 0) {
            System.out.println("Failed to insert TEQ role");
        }
        int utscRoleId = dbInsert.insertRole(Roles.UTSC.name());
        if (utscRoleId < 0) {
            System.out.println("Failed to insert UTSC role");
        }
        // Creating 3 default users
        dbi.insertUser("UTSC", "alice@utsc.ca", "123456");
        System.out.println("Email alice@utsc.ca created");
        System.out.println("Role set to UTSC");
        System.out.println("Password: 123456");
        dbi.insertUser("TEQ", "bob@teq.ca", "123456");
        System.out.println("Email bob@teq.ca created");
        System.out.println("Role set to TEQ");
        System.out.println("Password: 123456");
        dbi.insertUser("ORGANIZATION", "charl@teq.ca", "123456");
        System.out.println("Email charl@teq.ca created");
        System.out.println("Role set to ORGANIZATION");
        System.out.println("Password: 123456");

    }

    // Function for logging in
    private void logIn()
    {
        System.out.println("Enter email");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        usermail = scanner.nextLine();
        System.out.println("Enter password");
        password = scanner.nextLine();
        // Creating an instance of logging in
        auth = new AuthenticateImpl(dbs.getUserId(usermail));
        // Logging in
        try{
            if(auth.authenticate(password))
            {
                System.out.println("Log in successful");
            }
            else
            {
                System.out.println("Log in fail");
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            System.out.println("Check algorithm file and restart the program");
        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println("Encoding failed. Check system file and restart the program");
        }

    }

    // Function for logging out
    private void logOut()
    {
        usermail = null;
        password = null;
        auth.deauthenticate();
    }

    // Function for parsing an excel file
    private void parseFromFile()
    {
        // Get excel file name
        System.out.println("Enter file name");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        // Parse the excel file
        try{
            parsed.getFromExcel(filename);
        }
        catch (TemplateNullException e) {
        	System.out.println("Template returned a null exception");
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
    
    public static void displayStarterPage() {
        @SuppressWarnings("unused")
        StarterWindow starterWindow = new StarterWindow();
    }

    // Main method - uncomment out to use cmd
    /**
    public static void main(String[] args)
    {
        Connection connection = DatabaseDriver.connectOrCreateDatabase();
        cmdTerminal terminalInstance = new cmdTerminal();
        if (args.length > 0) {
            if (args[0].equals("-2")) {
                try {
                    DatabaseDriver.clear(connection);
                    System.out.println("All tables have been dropped");
                } catch (ConnectionFailedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else if (args[0].equals("-1")) {
                // initialize database here (don't forget to add the first user, etc)
                try {
                    terminalInstance.initialize(connection);
                    System.out.println("Database has been initialized");
                } catch (ConnectionFailedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            displayStarterPage();
            System.out.println("Welcome to the management system");
            // Integer correspond to command menu
            int cmdNum = 1;
            // Instance of cmd terminal
            while (cmdNum != 0) {
                terminalInstance.printCmdMenu();
                @SuppressWarnings("resource")
                Scanner nextCommand = new Scanner(System.in);
                cmdNum = nextCommand.nextInt();
                System.out.println("-----------------------------------------------------------");
                switch (cmdNum) {
                    case 1:
                        terminalInstance.logIn();
                        ;
                        break;
                    case 2:
                        terminalInstance.logOut();
                        ;
                        break;
                    case 3:
                        terminalInstance.parseFromFile();
                        ;
                        break;
                    case 4:
                        terminalInstance.printRecentExcel();
                        ;
                        break;
                }
            }
            System.out.println("Thank you for using the system ");
        }
        
    }*/

}