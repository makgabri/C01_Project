package org.jth.GUI.Orgnization;

import org.jth.exceptions.NotExcelException;
import org.jth.exceptions.TemplateNullException;
import org.jth.parsing.ParsingExcel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class OrganizationChooseWindow extends JFrame implements ActionListener {
    private Container container = getContentPane();

    private JButton uploadButton = new JButton("Upload");
    //private JButton checkUploadedButton = new JButton("Check Uploaded");
    private JButton removeUploadFileButton = new JButton("Remove Upload File");
    private static final long SLEEP_TIME = 3 * 1000;
    private ParsingExcel parsingExcel = ParsingExcel.getInstance();
    private Boolean uploaded = false;

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
        gridbag.setConstraints(removeUploadFileButton, c);
        //gridbag.setConstraints(checkUploadedButton, c);
        container.add(uploadButton);
        //container.add(checkUploadedButton);
        container.add(removeUploadFileButton);

        uploadButton.addActionListener(this);
        //checkUploadedButton.addActionListener(this);
        removeUploadFileButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == uploadButton) {
            // TODO check for upload!
            if(!uploaded) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.setCurrentDirectory(new java.io.File("Users/"));
                jfc.setDialogTitle("Choice Template");
                jfc.showDialog(new JLabel(), "Select");
                try {
                    File file = jfc.getSelectedFile();
                    if (file.isFile()) {
                        parsingExcel.setUpTemplates();
                        parsingExcel.getFromExcel(jfc.getSelectedFile().getAbsolutePath());
                        UploadSuccessWindow uploadSuccessWindow = new UploadSuccessWindow();
                        parsingExcel.printTemplate();
                    } else {
                        throw new NotExcelException();
                    }
                } catch (NotExcelException notExcel) {
                    NotExcelWindow notExcelWindow = new NotExcelWindow();
                } catch (NullPointerException nullPointer) {
                    System.out.println("User did not choice any file");
                } catch (TemplateNullException NULLException) {
                    NULLException.printStackTrace();
                } catch (IOException IO) {
                    IO.printStackTrace();
                }
            } else {
                AlreadyUploadWindow alreadyUploadWindow = new AlreadyUploadWindow();
            }
        } else {
            parsingExcel.dropAllTheTemplates();
            CancelUploadFileWindow cancelUploadFileWindow = new CancelUploadFileWindow();
        }
    }
}
