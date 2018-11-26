package org.jth.GUI.UTSC;

import org.jth.GUI.Windows.PasswordNotMatchWindow;
import org.jth.GUI.Windows.SignUpSuccessWindow;
import org.jth.GUI.Windows.StarterWindow;
import org.jth.GUI.app.Tracker;
import org.jth.databaseHelper.DatabaseInsertHelperImpl;
import org.jth.user.Roles;
import org.jth.user.TEQStaff;
import org.jth.user.UTSCStaff;
import org.jth.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class UTSCSignUpWindow extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JPanel textPanel = new JPanel();

    private User user;

    private JTextField firstNameField = new JTextField(15);
    private JTextField lastNameField = new JTextField(15);
    private JTextField emailField = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField(15);
    private JPasswordField conformPasswordField = new JPasswordField(15);

    private JButton signUpButton = new JButton("Sign Up");

    private Tracker tracker = Tracker.getInstance();


    public UTSCSignUpWindow() {
        super("UTSC Sign Up Menu");
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

        JLabel firstName = new JLabel("First name :");
        JLabel lastName = new JLabel("Last name :");
        JLabel password = new JLabel("Password :");
        JLabel conformPassword = new JLabel("Conform Password :");
        JLabel email = new JLabel("Email :");


        textPanel.add(firstName);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(firstNameField, c);
        textPanel.add(firstNameField);

        textPanel.add(lastName);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(lastNameField, c);
        textPanel.add(lastNameField);

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

        signUpButton.addActionListener(this);

        container.add(textPanel);
        container.add(signUpButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == signUpButton) {
            if(!new String(passwordField.getPassword()).equals(
                    new String(conformPasswordField.getPassword()))) {
                PasswordNotMatchWindow passwordNotMatchWindow = new PasswordNotMatchWindow();
            } else {
                DatabaseInsertHelperImpl databaseInsertHelper = new DatabaseInsertHelperImpl();
                Map<String, String> userInfo = databaseInsertHelper.insertUser(Roles.TEQ.name(), emailField.getText(),
                        new String(passwordField.getPassword()));
                user = new UTSCStaff(firstNameField.getText(), lastNameField.getText(),
                        userInfo.get("userId"), emailField.getText(), userInfo.get("creationDate"));
                SignUpSuccessWindow signUpSuccessWindow = new SignUpSuccessWindow(user);
                ((StarterWindow) tracker.getWindow("start")).setInvisible();
                setVisible(false);
                dispose();
                this.dispose();
            }
        }
    }
}
