package org.jth.GUI.Orgnization;

import org.jth.exceptions.NotExcelException;
import org.jth.parsing.ParsingExcel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
        if(e.getSource() == uploadButton) {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jfc.setCurrentDirectory(new java.io.File("Users/"));
            jfc.setDialogTitle("Choice Template");
            jfc.showDialog(new JLabel(), "Select");
            try {
                File file = jfc.getSelectedFile();
                ParsingExcel parsingExcel = ParsingExcel.getInstance();
                if(file.isFile()) {
                    parsingExcel.getFromExcel(jfc.getSelectedFile().getAbsolutePath());
                    UploadSuccessWindow uploadSuccessWindow = new UploadSuccessWindow();
                } else {
                    throw new NotExcelException();
                }
            } catch (NotExcelException notExcel) {
                NotExcelWindow notExcelWindow = new NotExcelWindow();
            } catch (NullPointerException nullPointer) {
                System.out.println("User did not choice any file");
            } catch (IOException io) {
                io.printStackTrace();
            }
        } else {

        }
    }
}
