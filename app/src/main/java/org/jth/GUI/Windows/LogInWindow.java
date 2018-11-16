package org.jth.GUI.Windows;

import javax.swing.*;
import java.awt.*;

public class LogInWindow extends JFrame {

    private JPanel texts = new JPanel(new GridLayout());

    private JTextField idField = new JTextField(10);
    private JTextField passwordField = new JPasswordField(10);

    public LogInWindow() {
        super("User Choice");
        setLayout(new FlowLayout());
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        drawLoginWindow();
        setVisible(true);
    }

    private void drawLoginWindow() {
        Container contentPane = getContentPane();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        getContentPane().setLayout(gridbag);

        JLabel id = new JLabel("Id: ");
        JLabel password = new JLabel("Password: ");

        getContentPane().add(id);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(idField, c);
        getContentPane().add(idField);


        getContentPane().add(password);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(passwordField, c);
        getContentPane().add(passwordField);
    }
}
