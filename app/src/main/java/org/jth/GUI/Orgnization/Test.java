package org.jth.GUI.Orgnization;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Test extends JFrame implements ActionListener {
    JButton open = new JButton("open");;

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        this.add(open);
        this.setSize(100,100);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        open.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfc.showDialog(new JLabel(), "Select");
        File file = jfc.getSelectedFile();
        if (file.isDirectory()) {
            System.out.println("文件夹:" + file.getAbsolutePath());
        } else if (file.isFile()) {
            System.out.println("文件:" + file.getAbsolutePath());
        }
        System.out.println(jfc.getSelectedFile().getName());

    }
}