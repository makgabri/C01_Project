package org.jth.GUI.Windows;

import javax.swing.*;
import java.awt.*;

public class LogInWindow extends JFrame {

    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JPanel Texts = new JPanel();
    private Container contentPane = getContentPane();

    private JButton conformButton = new JButton("Log In");

    private JTextField idField = new JTextField(10);
    private JTextField passwordField = new JPasswordField(10);

    public LogInWindow() {
        super("User Choice");
        setLayout(new FlowLayout());
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.setLocationRelativeTo(null);
        drawLoginWindow();
        setVisible(true);
        contentPane.setLayout(new FlowLayout());
    }

    private void drawLoginWindow() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        Texts.setLayout(gridbag);

        JLabel id = new JLabel("Id: ");
        JLabel password = new JLabel("Password: ");

        Texts.add(id);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(idField, c);
        Texts.add(idField);


        Texts.add(password);

        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(passwordField, c);
        Texts.add(passwordField);

        buttonPanel.add(conformButton);

        contentPane.add(Texts);
        contentPane.add(buttonPanel);

    }
}
