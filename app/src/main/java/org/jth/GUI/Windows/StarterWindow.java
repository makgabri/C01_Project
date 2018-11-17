package org.jth.GUI.Windows;

import org.jth.GUI.Orgnization.*;
import org.jth.user.Roles;
import org.jth.user.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.management.relation.Role;
import javax.swing.*;

public class StarterWindow extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private GridBagConstraints gbc = new GridBagConstraints();

    private JPanel buttons = new JPanel(new GridBagLayout());

    private JButton utsc = new JButton("UTSC Staff");
    private JButton teq = new JButton("TEQ");
    private JButton organization = new JButton("Organization");

    private JButton logIn = new JButton("Log In");
    private JButton signUp = new JButton("Sign Up");
    private JButton back = new JButton("Back");

    private Boolean clickOrganization = false;
    private Boolean clickUTSC = false;
    private Boolean clickTEQ = false;


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == utsc || e.getSource() == teq || e.getSource() == organization) {
            cleanWindow();
            drawStarterWindow(2);
            if(e.getSource() == organization) {
                clickOrganization = true;
            } else if(e.getSource() == teq) {
                clickTEQ = true;
            } else {
                clickUTSC = true;
            }
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
                LogInWindow logInWindow = new LogInWindow(Roles.ORGANIZATION);
            } else if(clickUTSC) {
                System.out.println("UTSC login");
                LogInWindow logInWindow = new LogInWindow(Roles.UTSC);
            } else {
                System.out.println("TEQ login");
                LogInWindow logInWindow = new LogInWindow(Roles.TEQ);
            }

        }

        if(e.getSource() == signUp) {
            if(clickOrganization) {
                OrganizationSignUpWindow orgnizationSignUpWindow = new OrganizationSignUpWindow();
            } else if(clickUTSC) {
                System.out.println("UTSC sign Up");
            } else {
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
            buttons.add(utsc, gbc);
            buttons.add(teq, gbc);
            buttons.add(organization, gbc);
        } else {
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
    }

    private void cleanWindow() {
        buttons.removeAll();
        repaint();
    }
}
