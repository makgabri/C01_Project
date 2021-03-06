package org.jth.GUI.TEQ;

import org.jth.GUI.Orgnization.AlreadyUploadWindow;
import org.jth.GUI.Orgnization.CancelUploadFileWindow;
import org.jth.GUI.Orgnization.NotExcelWindow;
import org.jth.GUI.Orgnization.UploadSuccessWindow;
import org.jth.GUI.Queries.QueryPage;
import org.jth.GUI.Windows.StarterWindow;
import org.jth.GUI.app.Tracker;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;
import org.jth.databaseHelper.DatabaseUpdateHelperImpl;
import org.jth.exceptions.NotExcelException;
import org.jth.exceptions.TemplateNullException;
import org.jth.parsing.ParsingExcel;
import org.jth.templates.Execution;
import org.jth.user.User;
import org.jth.databaseHelper.DatabaseDriver;
import org.jth.databaseHelper.DatabaseSelectHelper;
import org.jth.databaseHelper.DatabaseSelectHelperImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TEQChooseWindow extends JFrame implements ActionListener {
    private Container container = getContentPane();

    private JLabel title = new JLabel("<html><b><font size=+1>TEQ Menu</font></b></html>");
    private JButton uploadButton = new JButton("Upload");
    //private JButton checkUploadedButton = new JButton("Check Uploaded");
    private JButton removeUploadFileButton = new JButton("Remove Upload File");
    private JButton uploadStatusButton = new JButton("Check Upload Status");
    private JButton queryButton = new JButton("Perform a Query");
    public JButton logout = new JButton("Logout");
    private static final long SLEEP_TIME = 3 * 1000;
    private ParsingExcel parsingExcel = ParsingExcel.getInstance();
    private Boolean uploaded = false;
    private User user;
    private DatabaseSelectHelper dbs;
    private Tracker tracker = Tracker.getInstance();
    private Execution execution = new Execution();

    public TEQChooseWindow(User user) {
        super("TEQ Main Menu");
        drawWindow();
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.user = user;
        this.dbs = new DatabaseSelectHelperImpl();
    }

    private void drawWindow() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        container.setLayout(gridbag);
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        gridbag.setConstraints(title, c);
        gridbag.setConstraints(uploadButton, c);
        gridbag.setConstraints(queryButton, c);
        gridbag.setConstraints(removeUploadFileButton, c);
        gridbag.setConstraints(uploadStatusButton, c);
        gridbag.setConstraints(logout, c);
        //gridbag.setConstraints(checkUploadedButton, c);
        container.add(title);
        container.add(uploadButton);
        container.add(queryButton);
        //container.add(checkUploadedButton);
        container.add(removeUploadFileButton);
        container.add(uploadStatusButton);
        container.add(logout);

        uploadButton.addActionListener(this);
        //checkUploadedButton.addActionListener(this);
        removeUploadFileButton.addActionListener(this);
        uploadStatusButton.addActionListener(this::checkUpload);
        queryButton.addActionListener(this::openQueryWindow);
        logout.addActionListener(this);
    }

    private void openQueryWindow(ActionEvent e) {
        QueryPage queryPage = new QueryPage();
        tracker.changePrevious("teq");
        setVisible(false);
    }

    private void checkUpload(ActionEvent e) {
        Boolean uploaded  = dbs.checkUploaded(user.getUserId());
        if (uploaded == null || !uploaded) {
            JOptionPane.showMessageDialog(this, "You have not uploaded any data.");
        } else {
            JOptionPane.showMessageDialog(this, "You have uploaded your data.");
        }
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
                        new DatabaseUpdateHelperImpl().updateUploadStatus(user.getUserId(), true);
                        Connection tempConn = DatabaseDriver.connectOrCreateDatabase();
                        execution.execute(tempConn, jfc.getSelectedFile().getAbsolutePath());
                        try {
                          tempConn.close();
                        } catch (SQLException e1) {
                          e1.printStackTrace();
                        }
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
        } else if (e.getSource() == logout) {
            setVisible(false);
            ((StarterWindow) tracker.getWindow("start")).setVisible();
            dispose();
        } else {
            parsingExcel.dropAllTheTemplates();
            CancelUploadFileWindow cancelUploadFileWindow = new CancelUploadFileWindow();
            new DatabaseUpdateHelperImpl().updateUploadStatus(user.getUserId(), false);
        }
    }
    
    public void setInvisible() {
       setVisible(false);
    }
    
    public void setVisible() {
      setVisible(true);
    }
}
