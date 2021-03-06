package org.jth.GUI.Windows;

import org.jth.GUI.Orgnization.*;
import org.jth.GUI.TEQ.TEQSignUpWindow;
import org.jth.GUI.UTSC.UTSCSignUpWindow;
import org.jth.databaseHelper.DatabaseDriver;
import org.jth.databaseHelper.DatabaseInsertHelper;
import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.exceptions.ConnectionFailedException;
import org.jth.templates.TemplateDriver;
import org.jth.user.Roles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

public class StarterWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private GridBagConstraints gbc = new GridBagConstraints();

    private JPanel buttons = new JPanel(new GridBagLayout());

    private JLabel title = new JLabel("<html><b><u><font size=+2>I-Care Template App</font></u></b></html>");
    private JLabel creators = new JLabel("Application developed by: Jawa & The Hutts");
    
    private JLabel utscLogin = new JLabel("<html><b><font size=+1>UTSC Login</font></b></html>");
    private JLabel teqLogin = new JLabel("<html><b><font size=+1>TEQ Login</font></b></html>");
    private JLabel organizationLogin = new JLabel("<html><b><font size=+1>Organization Login</font></b></html>");
    private JButton utsc = new JButton("UTSC Staff");
    private JButton teq = new JButton("TEQ");
    private JButton organization = new JButton("Organization");

    private JButton logIn = new JButton("Log In");
    private JButton signUp = new JButton("Sign Up");
    private JButton clearDB = new JButton("Clear Database");
    private JButton initDB = new JButton("Initialize Database");
    private JButton back = new JButton("Back");

    private Boolean clickOrganization = false;
    private Boolean clickUTSC = false;
    private Boolean clickTEQ = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == utsc) {
            cleanWindow();
            drawStarterWindow(2);
            clickUTSC = true;
        } else if (e.getSource() == teq) {
            cleanWindow();
            drawStarterWindow(3);
            clickTEQ = true;
        } else if (e.getSource() == organization) {
            cleanWindow();
            drawStarterWindow(4);
            clickOrganization = true;
        }

        if (e.getSource() == back) {
            clickOrganization = false;
            clickTEQ = false;
            clickUTSC = false;
            cleanWindow();
            drawStarterWindow(1);
        }

        if(e.getSource() == logIn) {
            if(clickOrganization) {
                System.out.println("Organization login");
                @SuppressWarnings("unused")
                LogInWindow logInWindow = new LogInWindow(Roles.ORGANIZATION);
            } else if(clickUTSC) {
                System.out.println("UTSC login");
                @SuppressWarnings("unused")
                LogInWindow logInWindow = new LogInWindow(Roles.UTSC);
            } else if(clickTEQ) {
                System.out.println("TEQ login");
                @SuppressWarnings("unused")
                LogInWindow logInWindow = new LogInWindow(Roles.TEQ);
            }

        }

        if(e.getSource() == signUp) {
            if(clickOrganization) {
                @SuppressWarnings("unused")
                OrganizationSignUpWindow orgnizationSignUpWindow = new OrganizationSignUpWindow();
            } else if(clickUTSC) {
                @SuppressWarnings("unused")
                UTSCSignUpWindow utscSignUpWindow = new UTSCSignUpWindow();
                System.out.println("UTSC sign Up");
            } else {
                @SuppressWarnings("unused")
                TEQSignUpWindow teqSignUpWindow = new TEQSignUpWindow();
                System.out.println("TEQ sign Up");
            }

        }
    }

    public StarterWindow() {
        super("Main Menu");
        setLayout(new FlowLayout());
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout();
        drawStarterWindow(1);
        addActionToButtons();

        setVisible(true);
        setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void setLayout() {
        setLayout(new GridBagLayout());
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.insets = new Insets(5,5,5,5);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    /**
     * draw the starter window or the log in sign up window.
     * @param choice 1 starter window 2 log in window
     */
    private void drawStarterWindow(int choice) {
        if(choice == 1) {
            buttons.add(title, gbc);
            buttons.add(utsc, gbc);
            buttons.add(teq, gbc);
            buttons.add(organization, gbc);
            buttons.add(initDB, gbc);
            buttons.add(clearDB, gbc);
            buttons.add(creators, gbc);
        } else if (choice == 2) {
            buttons.add(utscLogin, gbc);
            buttons.add(logIn, gbc);
            buttons.add(signUp, gbc);
            buttons.add(back, gbc);
        } else if (choice == 3) {
          buttons.add(teqLogin, gbc);
          buttons.add(logIn, gbc);
          buttons.add(signUp, gbc);
          buttons.add(back, gbc);
        } else {
          buttons.add(organizationLogin, gbc);
          buttons.add(logIn, gbc);
          buttons.add(signUp, gbc);
          buttons.add(back, gbc);
        }

        add(buttons);
        setVisible(true);
    }



    /**
     * Add Action Listener to all the buttons.
     */
    private void addActionToButtons() {
        utsc.addActionListener(this);
        teq.addActionListener(this);
        organization.addActionListener(this);
        logIn.addActionListener(this);
        signUp.addActionListener(this);
        back.addActionListener(this);
        initDB.addActionListener(this::initalizeDB);
        clearDB.addActionListener(this::clearDB);
    }

    public void setInvisible() {
        setVisible(false);
    }
    
    public void setVisible() {
        setVisible(true);
    }
    
    private void clearDB(ActionEvent event) {
        Connection connection = DatabaseDriver.connectOrCreateDatabase();
        try {
            DatabaseDriver.clear(connection);
            System.out.println("Database has been cleared");
            TemplateDriver.clear(connection);
        } catch (ConnectionFailedException e) {
            JOptionPane.showMessageDialog(this, "The database has not been initialized, " +
                    "so you cannot clear it");
        }
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
    }

    private void initalizeDB(ActionEvent event) {
        Connection connection = DatabaseDriver.connectOrCreateDatabase();
        try {
            DatabaseDriver.initialize(connection);
        } catch (ConnectionFailedException e) {
            e.printStackTrace();
        }
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
        dbInsert.insertUser("UTSC", "alice@utsc.ca", "123456");
        System.out.println("Email alice@utsc.ca created");
        System.out.println("Role set to UTSC");
        System.out.println("Password: 123456");
        dbInsert.insertUser("TEQ", "bob@teq.ca", "123456");
        System.out.println("Email bob@teq.ca created");
        System.out.println("Role set to TEQ");
        System.out.println("Password: 123456");
        dbInsert.insertUser("ORGANIZATION", "charl@teq.ca", "123456");
        System.out.println("Email charl@teq.ca created");
        System.out.println("Role set to ORGANIZATION");
        System.out.println("Password: 123456");


    }


    private void cleanWindow() {
        buttons.removeAll();
        repaint();
    }
}
