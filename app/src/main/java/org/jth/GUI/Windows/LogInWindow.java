package org.jth.GUI.Windows;

import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.databaseHelper.DatabaseSelectHelper;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;
import org.jth.user.Roles;
import org.jth.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class LogInWindow extends JFrame implements ActionListener {
    private Roles roles;

    private User user;
    private DatabaseSelectHelper databaseSelectHelper = new DatabaseSelectHelperImpl();

    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JPanel textPanel = new JPanel();
    private Container container = getContentPane();

    private JButton logInButton = new JButton("Log In");
    private JTextField emailField = new JTextField(10);
    private JTextField passwordField = new JPasswordField(10);

    public LogInWindow(Roles roles) {
        super("Log In");
        this.roles = roles;
        setLayout(new FlowLayout());
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.setLocationRelativeTo(null);
        drawLoginWindow();
        setVisible(true);
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        this.setLocationRelativeTo(null);
    }

    private void drawLoginWindow() {

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        textPanel.setLayout(gridbag);

        JLabel id = new JLabel("Email: ");
        JLabel password = new JLabel("Password: ");

        textPanel.add(id);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(emailField, c);
        textPanel.add(emailField);


        textPanel.add(password);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(passwordField, c);
        textPanel.add(passwordField);

        buttonPanel.add(logInButton);

        logInButton.addActionListener(this);

        container.add(textPanel);
        container.add(logInButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logInButton) {
            if(roles == Roles.ORGANIZATION) {
                try {
                    user = databaseSelectHelper.getUser(databaseSelectHelper.getUserId(emailField.getText()));
                    if (user.logIn(databaseSelectHelper.getUserId(emailField.getText()), passwordField.getText())) {

                        LoginSuccessOrFailWindow loginSuccessOrFailWindow = new LoginSuccessOrFailWindow(1, roles, user);
                    } else {
                        LoginSuccessOrFailWindow loginSuccessOrFailWindow = new LoginSuccessOrFailWindow(1, roles, user);
                    }
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                    ex.printStackTrace();
                }
                // TODO Organization Login uncomment the code below
                // if(success)
                //  dispose();
                //  LoginSuccessOrFailWindow loginSuccessOrFailWindow = new LoginSuccessOrFailWindow(1, roles);
                // if(fail)
                //  LoginSuccessOrFailWindow loginSuccessOrFailWindow = new LoginSuccessOrFailWindow(0, roles);
            } else if(roles == Roles.UTSC) {

            } else {

            }

        }
    }
}
