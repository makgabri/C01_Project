package org.jth.GUI.Queries;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.*;
import org.jth.databaseHelper.DatabaseDriver;
import org.jth.templates.TemplateSelectHelperImpl;

public class QueryPage extends JFrame implements ActionListener {
    
    public static void main(String[] args) {
        new QueryPage();
    }
  
    private static final long serialVersionUID = 1L;
    
    // Change connection to current existing connection in app
    private Connection conn = DatabaseDriver.connectOrCreateDatabase();
    private TemplateSelectHelperImpl tsh = new TemplateSelectHelperImpl();
    private GridBagConstraints gbc = new GridBagConstraints();

    //************************************************************

    private JPanel buttons = new JPanel(new GridBagLayout());

    //************************************************************

    private JButton searchSpecific = new JButton("Search record by field");
    private JButton searchTotalStats = new JButton("Search total statistics of field");
    
    private JButton searchTotalAction = new JButton("Search Total");
    private JButton searchSpecificAction = new JButton("Search Specific");
    private JButton back = new JButton("Back");
    
    private JLabel templateTypeHeader = new JLabel("Template Type:    ");
    private JLabel fieldTypeHeader = new JLabel("Field Type:    ");
    private JLabel fieldSearchHeader = new JLabel("Search Item:    ");
    private JLabel result = new JLabel("");
    @SuppressWarnings({"unchecked", "rawtypes"})
    private JComboBox templateList = new JComboBox(tsh.getTables(conn));
    private JComboBox<String> fieldList = new JComboBox<String>();
    private JTextField searchTextBox = new JTextField("");

    //************************************************************


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchSpecific) {
            // Search by specific page
            cleanWindow();
            drawStarterWindow(2);
        } else if(e.getSource() == searchTotalStats){
          // search Total stats page
          cleanWindow();
          drawStarterWindow(3);
        } else if(e.getSource() == searchTotalAction){
          // search Total action
          String templateName = (String) templateList.getSelectedItem();
          String field = (String) fieldList.getSelectedItem();
          String condition = searchTextBox.getText();
          int totalStats = tsh.getTotalFromField(conn, templateName, field,
              condition);
          String temp =  "The following total number of records with condition"
              + " are: " + totalStats;
          result.setText(temp);
        } else if(e.getSource() == searchSpecificAction) {
          // search specific action
          String templateName = (String) templateList.getSelectedItem();
          String field = (String) fieldList.getSelectedItem();
          String condition = searchTextBox.getText();
          ArrayList<String> uniqueIVList = tsh.getUniqueIV(conn, templateName,
              field, condition);
          String temp =  "The following Unique Identifier Value(s) contain "
                  + "what you searched for:        ";
          if (uniqueIVList.size() == 0) {
            temp += "None";
          } else {
            for (String uiv : uniqueIVList) {
              temp += uiv + ",     ";
            }
            temp = temp.substring(0, temp.length()-7);
          }
          result.setText(temp);
        } else if (e.getSource() == back) {
            result.setText("");
            cleanWindow();
            drawStarterWindow(1);
            this.dispose();
        } else if (e.getSource() == templateList) {
            fieldList.removeAllItems();
            for (String field : tsh.getColumnFromTable(conn,
                (String) templateList.getSelectedItem())) {
                  fieldList.addItem(field);
            }
        }
    }

    public QueryPage() {
        super("Queries");
        setLayout(new FlowLayout());
        setSize(1250, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout();
        drawStarterWindow(1);
        addActionToButtons();

        setVisible(true);
        setResizable(false);
    }
    
    private void setLayout() {
        setLayout(new GridBagLayout());
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
  
        gbc.insets = new Insets(5,5,5,5);
  
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    /**
     * draw the starter window or the log in sign up window.
     * @param choice 1 starter window 2 log in window
     */
    private void drawStarterWindow(int choice) {
        if(choice == 1) {
            buttons.add(searchSpecific, gbc);
            buttons.add(searchTotalStats, gbc);
            buttons.add(back, gbc);
        } else if (choice == 2) {
            // Search Specific Row
            buttons.add(templateTypeHeader);
            buttons.add(templateList, gbc);
            buttons.add(fieldTypeHeader);
            buttons.add(fieldList, gbc);
            buttons.add(fieldSearchHeader);
            buttons.add(searchTextBox, gbc);
            buttons.add(searchSpecificAction, gbc);
            buttons.add(result, gbc);
            buttons.add(back, gbc);
        } else {
          // Search Total Stats
          buttons.add(templateTypeHeader);
          buttons.add(templateList, gbc);
          buttons.add(fieldTypeHeader);
          buttons.add(fieldList, gbc);
          buttons.add(fieldSearchHeader);
          buttons.add(searchTextBox, gbc);
          buttons.add(searchTotalAction, gbc);
          buttons.add(result, gbc);
          buttons.add(back, gbc);
        }

        add(buttons);
        setVisible(true);
    }



    /**
     * Add Action Listener to all the buttons.
     */
    private void addActionToButtons() {
        searchSpecific.addActionListener(this);
        searchTotalStats.addActionListener(this);
        searchSpecificAction.addActionListener(this);
        searchTotalAction.addActionListener(this);
        templateList.addActionListener(this);
        fieldList.addActionListener(this);
        searchTextBox.addActionListener(this);
        back.addActionListener(this);
    }

    private void cleanWindow() {
        buttons.removeAll();
        repaint();
    }
}
