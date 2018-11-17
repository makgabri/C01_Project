package org.jth.GUI.Orgnization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganizationChooseWindow extends JFrame implements ActionListener {
    private Container container = getContentPane();

    private JButton upload = new JButton("Upload");

    public OrganizationChooseWindow() {
        super("Organization Main Menu");
        drawWindow();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void drawWindow() {
        container.add(upload);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
