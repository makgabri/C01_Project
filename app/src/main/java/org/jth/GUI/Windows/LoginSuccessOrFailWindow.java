package org.jth.GUI.Windows;

import org.jth.GUI.Orgnization.OrganizationChooseWindow;
import org.jth.GUI.Orgnization.TEQChooseWindow;
import org.jth.GUI.app.Tracker;
import org.jth.user.Roles;
import org.jth.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginSuccessOrFailWindow extends JFrame implements ActionListener {
    private Roles roles;

    private Container container = getContentPane();
    private JPanel textPanel = new JPanel(new GridBagLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout());

    private JButton loginSuccessButton = new JButton("OK");
    private JButton loginFailButton = new JButton("Back");

    private JLabel loginSuccess = new JLabel("Sign in Success!");
    private JLabel loginfail = new JLabel("Sign in Failed!");

    private User user;
    
    private Tracker tracker = Tracker.getInstance();

    public LoginSuccessOrFailWindow(int success, Roles roles, User user) {
        super("Login State");
        this.roles = roles;
        drawWindow(success);
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.user = user;
    }

    private void drawWindow(int choice) {

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        textPanel.setLayout(gridbag);

        if(choice == 1) {
            textPanel.add(loginSuccess);
        } else {
            textPanel.add(loginfail);
        }
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(textPanel, c);

        if(choice == 1) {
            buttonPanel.add(loginSuccessButton);
        } else {
            buttonPanel.add(loginFailButton);
        }

        loginSuccessButton.addActionListener(this);
        loginFailButton.addActionListener(this);

        container.add(textPanel);
        container.add(buttonPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginSuccessButton) {
            dispose();
            if(roles == Roles.UTSC) {

            } else if(roles == Roles.ORGANIZATION){
                OrganizationChooseWindow organizationChooseWindow = new OrganizationChooseWindow(user);
            } else {
                TEQChooseWindow teqChooseWindow = new TEQChooseWindow(user);
                tracker.addWindow("teq", teqChooseWindow);
            }
        } else {
            dispose();
        }
    }
}
