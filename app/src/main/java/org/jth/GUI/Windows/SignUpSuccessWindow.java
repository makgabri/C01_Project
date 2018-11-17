package org.jth.GUI.Windows;

import org.jth.GUI.Orgnization.OrganizationChooseWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpSuccessWindow extends JFrame implements ActionListener {
    private Container container = getContentPane();
    private JPanel textPanel = new JPanel(new GridBagLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JButton exit = new JButton("Back");

    public SignUpSuccessWindow() {
        super("Sign Up Success!");
        drawWindow();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        this.setLocationRelativeTo(null);
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
            OrganizationChooseWindow organizationChooseWindow = new OrganizationChooseWindow();
        }
    }
}
