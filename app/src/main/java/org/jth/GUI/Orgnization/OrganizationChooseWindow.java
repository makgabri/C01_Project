package org.jth.GUI.Orgnization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganizationChooseWindow extends JFrame implements ActionListener {
    private Container container = getContentPane();

    private JButton uploadButton = new JButton("Upload");
    private JButton checkUploadedButton = new JButton("check Uploaded");

    public OrganizationChooseWindow() {
        super("Organization Main Menu");
        drawWindow();
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void drawWindow() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        container.setLayout(gridbag);
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(uploadButton, c);
        gridbag.setConstraints(checkUploadedButton, c);
        container.add(uploadButton);
        container.add(checkUploadedButton);

        uploadButton.addActionListener(this);
        checkUploadedButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
