package org.jth.GUI.Windows;

import org.jth.GUI.Orgnization.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Windows extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private GridBagConstraints gbc = new GridBagConstraints();

    //************************************************************

    private JPanel buttons = new JPanel(new GridBagLayout());

    //************************************************************

    private JButton utsc = new JButton("UTSC Staff");
    private JButton teq = new JButton("TEQ");
    private JButton organization = new JButton("Organization");

    private JButton logIn = new JButton("Log In");
    private JButton signUp = new JButton("Sign Up");
    private JButton back = new JButton("Back");

    private Boolean clickOrganization = false;

    //************************************************************


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == utsc || e.getSource() == teq || e.getSource() == organization) {
            cleanWindow();
            drawStarterWindow(2);
        }
        if(e.getSource() == organization) {
            clickOrganization = true;
        } else if (e.getSource() == back) {
            cleanWindow();
            clickOrganization = false;
            drawStarterWindow(1);
        } else if(e.getSource() == logIn) {
            LogInWindow logInWindow = new LogInWindow();
        } else if(clickOrganization && e.getSource() == signUp) {
            OrganizationSignUpWindow orgnizationSignUpWindow = new OrganizationSignUpWindow();
        }
    }

    public Windows() {
        super("User Choice");
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
