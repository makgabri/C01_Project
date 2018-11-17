package org.jth.GUI.Windows;

import org.jth.user.Roles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInWindow extends JFrame implements ActionListener {
    private Roles roles;

    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JPanel textPanel = new JPanel();
    private Container container = getContentPane();

    private JButton logInButton = new JButton("Log In");
    private JTextField idField = new JTextField(10);
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

        JLabel id = new JLabel("Id: ");
        JLabel password = new JLabel("Password: ");

        textPanel.add(id);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(idField, c);
        textPanel.add(idField);


        textPanel.add(password);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(passwordField, c);
        textPanel.add(passwordField);

        buttonPanel.add(logInButton);

        logInButton.addActionListener(this);

        container.add(textPanel);
        container.add(logInButton);
    }

    private void checkLoginSuccessOrNot() {
        String id = idField.getText();
        String password = passwordField.getText();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logInButton) {
            if(roles == Roles.ORGANIZATION) {
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
