package org.jth.GUI.Orgnization;

import org.jth.GUI.Windows.PasswordNotMatchWindow;
import org.jth.GUI.Windows.SignUpSuccessWindow;
import org.jth.GUI.Windows.StarterWindow;
import org.jth.GUI.app.Tracker;
import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.user.Organization;
import org.jth.user.Roles;
import org.jth.user.SupportType;
import org.jth.user.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.*;

public class OrganizationSignUpWindow extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JPanel textPanel = new JPanel();

    private User user;

    private JTextField nameField = new JTextField(15);
    private JTextField emailField = new JTextField(15);
    private JTextField postalCodeField = new JTextField(15);
    //private JTextField supportTypeField = new JTextField(15);
    JComboBox supportTypeField = new JComboBox(getSupportType());
    private JPasswordField passwordField = new JPasswordField(15);
    private JPasswordField conformPasswordField = new JPasswordField(15);

    private JButton signUpButton = new JButton("Sign Up");

    private Tracker tracker = Tracker.getInstance();

    public OrganizationSignUpWindow() {
        super("Organization Sign Up Menu");
        drawAllTextFields();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void drawAllTextFields() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        textPanel.setLayout(gridbag);

        JLabel name = new JLabel("Organization name :");
        JLabel password = new JLabel("Password :");
        JLabel conformPassword = new JLabel("Conform Password :");
        JLabel email = new JLabel("Email :");
        JLabel postalCode = new JLabel("Postal Code :");
        JLabel supportType = new JLabel("Support Type :");


        textPanel.add(name);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(nameField, c);
        textPanel.add(nameField);

        textPanel.add(password);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(passwordField, c);
        textPanel.add(passwordField);

        textPanel.add(conformPassword);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(conformPasswordField, c);
        textPanel.add(conformPasswordField);

        textPanel.add(email);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(emailField, c);
        textPanel.add(emailField);

        textPanel.add(postalCode);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(postalCodeField, c);
        textPanel.add(postalCodeField);

        textPanel.add(supportType);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(supportTypeField, c);
        textPanel.add(supportTypeField);

        signUpButton.addActionListener(this);

        container.add(textPanel);
        container.add(signUpButton);
    }

    /**
     * capitalize all the letter and replace the space with underline
     * @param line input String
     * @return a String that is proper formed.
     */
    private String capitalizeAndReplaceSpaceWithUnderline(String line) {
        line = line.toUpperCase();
        return line.replaceAll(" ", "_");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = true;
        if(e.getSource() == signUpButton) {
            if(!new String(passwordField.getPassword()).equals(
                    new String(conformPasswordField.getPassword()))) {
                PasswordNotMatchWindow passwordNotMatchWindow = new PasswordNotMatchWindow();
            } else {
                String supportType = (String) supportTypeField.getSelectedItem();
                if (flag) {
                    DatabaseInsertHelperImpl databaseInsertHelper = new DatabaseInsertHelperImpl();
                    Map<String, String> userInfo = databaseInsertHelper.insertUser(Roles.ORGANIZATION.name(), emailField.getText(),
                            new String(passwordField.getPassword()));
                    user = new Organization(nameField.getText(), userInfo.get("userId"), emailField.getText(),
                            postalCodeField.getText(), SupportType.valueOf(supportType), userInfo.get("creationDate"));
                    SignUpSuccessWindow signUpSuccessWindow = new SignUpSuccessWindow(user);
                    ((StarterWindow) tracker.getWindow("start")).setInvisible();
                    setVisible(false);
                    dispose();
                    this.dispose();
                }
            }
        }
    }
    
    private String[] getSupportType() {
        java.util.LinkedList<String> list = new LinkedList<String>();
        for (SupportType s : SupportType.values()) {
            list.add(s.name());
        }
        return list.toArray(new String[list.size()]);
    }
}
