package org.jth.GUI.Windows;

import org.jth.GUI.Orgnization.OrganizationChooseWindow;
import org.jth.GUI.TEQ.TEQChooseWindow;
import org.jth.GUI.UTSC.UTSCChooseWindow;
import org.jth.GUI.UTSC.UTSCSignUpWindow;
import org.jth.user.Roles;
import org.jth.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpSuccessWindow extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JPanel textPanel = new JPanel(new GridBagLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JButton exit = new JButton("OK");

    private User user;

    public SignUpSuccessWindow(User user) {
        super("Sign Up Success!");
        drawWindow();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.user = user;
    }

    private void drawWindow() {
        JLabel notMatch = new JLabel("Sign Up Success!");
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        textPanel.setLayout(gridbag);

        textPanel.add(notMatch);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(textPanel, c);

        buttonPanel.add(exit);
        //panel.add(exit);

        exit.addActionListener(this);
        container.add(textPanel);
        container.add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exit) {
            this.dispose();
            if(user.getRole() == Roles.ORGANIZATION) {
                OrganizationChooseWindow organizationChooseWindow = new OrganizationChooseWindow(user);
            } else if(user.getRole() == Roles.TEQ) {
                TEQChooseWindow teqChooseWindow = new TEQChooseWindow(user);
            } else {
                UTSCChooseWindow utscChooseWindow = new UTSCChooseWindow(user);
            }
        }
    }
}
