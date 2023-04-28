package resources;

import java.awt.*;

//Here you can define all sizes and coordinates for all kind of components
public class Sizes {
    private Dimension size;
    private Toolkit tool;
    int screenHeight;
    int screenWidth;
    public void init() {
        size = tool.getDefaultToolkit().getScreenSize();
        screenHeight = (int) size.getHeight();
        screenWidth = (int) size.getWidth();
    }
    //Frames
    int frame_view_mainFrame_frameWidth = 1200;
    int frame_view_mainFrame_frameHeight = 800;

    //----Panels----
        //panel for the database connection (start screen)

    int panel_view_panelDatabaseConnection_panelWidth = 200;
    int panel_view_panelDatabaseConnection_panelHeight = 200;
    int panel_view_panelDatabaseConnection_panelX = 0;
    int panel_view_panelDatabaseConnection_panelY = 0;
        //panel which got the list with the tables on it
    int panel_panelTableSelection_panelWidth = 1200;
    int panel_panelTableSelection_panelHeight = 800;
    int panel_panelTableSelection_panelX = 0;
    int panel_panelTableSelection_panelY = 0;
        //panel which got the table from the db on it
    int panel_panelTableView_panelWidth = 800;
    int panel_panelTableView_panelHeight = 800;
    int panel_panelTableView_panelX = 400;
    int panel_panelTableView_panelY = 0;


    //Buttons
        //Connection button
    int button_panelDatabaseConnection_buttonConnect_buttonWidth = 300;
    int button_panelDatabaseConnection_buttonConnect_buttonHeight = 40;
    int button_panelDatabaseConnection_buttonConnect_buttonX = (screenWidth/2);
    int button_panelDatabaseConnection_buttonConnect_buttonY= 520;

    //----Text Fields----
        //Hostname
    int textField_panelDatabaseConnection_textFieldHostname_textFieldWidth = 300;
    int textField_panelDatabaseConnection_textFieldHostname_textFieldHeight = 50;
    int textField_panelDatabaseConnection_textFieldHostname_textFieldX = 200;
    int textField_panelDatabaseConnection_textFieldHostname_textFieldY = 200;
        //Port
    int textField_panelDatabaseConnection_textFieldPort_textFieldWidth = 300;
    int textField_panelDatabaseConnection_textFieldPort_textFieldHeight = 50;
    int textField_panelDatabaseConnection_textFieldPort_textFieldX = 200;
    int textField_panelDatabaseConnection_textFieldPort_textFieldY = 260;
     //Database name
    int textField_panelDatabaseConnection_textFieldDatabaseName_textFieldWidth = 300;
    int textField_panelDatabaseConnection_textFieldDatabaseName_textFieldHeight = 50;
    int textField_panelDatabaseConnection_textFieldDatabaseName_textFieldX = 200;
    int textField_panelDatabaseConnection_textFieldDatabaseName_textFieldY = 320;
     //Username
    int textField_panelDatabaseConnection_textFieldUsername_textFieldWidth = 300;
    int textField_panelDatabaseConnection_textFieldUsername_textFieldHeight = 50;
    int textField_panelDatabaseConnection_textFieldUsername_textFieldX = 200;
    int textField_panelDatabaseConnection_textFieldUsername_textFieldY = 380;
        //Password
    int textField_panelDatabaseConnection_textFieldPassword_textFieldWidth = 300;
    int textField_panelDatabaseConnection_textFieldPassword_textFieldHeight = 50;
    int textField_panelDatabaseConnection_textFieldPassword_textFieldX = 200;
    int textField_panelDatabaseConnection_textFieldPassword_textFieldY = 440;

    //----JLists----
        //list with all tables in it
    int jlist_panelTableSelection_jlistTableSelection_jlistWidth = 400;
    int jlist_panelTableSelection_jlistTableSelection_jlistHeight = 800;
    int jlist_panelTableSelection_jlistTableSelection_jlistX= 0;
    int jlist_panelTableSelection_jlistTableSelection_jlisty = 0;

    //----Tables----
        //Table with detailed view from DB
    int table_panelTableView_tableFromDB_tableWidth = 800;
    int table_panelTableView_tableFromDB_tableHeight = 800;
    int table_panelTableView_tableFromDB_tableX = 0;
    int table_panelTableView_tableFromDB_tableY = 0;

    //Getter

    public int getPanel_panelTableView_panelWidth() {
        return panel_panelTableView_panelWidth;
    }

    public int getPanel_panelTableView_panelHeight() {
        return panel_panelTableView_panelHeight;
    }

    public int getPanel_panelTableView_panelX() {
        return panel_panelTableView_panelX;
    }

    public int getPanel_panelTableView_panelY() {
        return panel_panelTableView_panelY;
    }

    public int getTable_panelTableView_tableFromDB_tableWidth() {
        return table_panelTableView_tableFromDB_tableWidth;
    }

    public int getTable_panelTableView_tableFromDB_tableHeight() {
        return table_panelTableView_tableFromDB_tableHeight;
    }

    public int getTable_panelTableView_tableFromDB_tableX() {
        return table_panelTableView_tableFromDB_tableX;
    }

    public int getTable_panelTableView_tableFromDB_tableY() {
        return table_panelTableView_tableFromDB_tableY;
    }

    public int getPanel_panelTableSelection_panelWidth() {
        return panel_panelTableSelection_panelWidth;
    }

    public int getPanel_panelTableSelection_panelHeight() {
        return panel_panelTableSelection_panelHeight;
    }

    public int getPanel_panelTableSelection_panelX() {
        return panel_panelTableSelection_panelX;
    }

    public int getPanel_panelTableSelection_panelY() {
        return panel_panelTableSelection_panelY;
    }

    public int getJlist_panelTableSelection_jlistTableSelection_jlistWidth() {
        return jlist_panelTableSelection_jlistTableSelection_jlistWidth;
    }

    public int getJlist_panelTableSelection_jlistTableSelection_jlistHeight() {
        return jlist_panelTableSelection_jlistTableSelection_jlistHeight;
    }

    public int getJlist_panelTableSelection_jlistTableSelection_jlistX() {
        return jlist_panelTableSelection_jlistTableSelection_jlistX;
    }

    public int getJlist_panelTableSelection_jlistTableSelection_jlisty() {
        return jlist_panelTableSelection_jlistTableSelection_jlisty;
    }

    public int getTextField_panelDatabaseConnection_textFieldPassword_textFieldWidth() {
        return textField_panelDatabaseConnection_textFieldPassword_textFieldWidth;
    }

    public int getTextField_panelDatabaseConnection_textFieldPassword_textFieldHeight() {
        return textField_panelDatabaseConnection_textFieldPassword_textFieldHeight;
    }

    public int getTextField_panelDatabaseConnection_textFieldPassword_textFieldX() {
        return textField_panelDatabaseConnection_textFieldPassword_textFieldX;
    }

    public int getTextField_panelDatabaseConnection_textFieldPassword_textFieldY() {
        return textField_panelDatabaseConnection_textFieldPassword_textFieldY;
    }

    public int getTextField_panelDatabaseConnection_textFieldUsername_textFieldWidth() {
        return textField_panelDatabaseConnection_textFieldUsername_textFieldWidth;
    }

    public int getTextField_panelDatabaseConnection_textFieldUsername_textFieldHeight() {
        return textField_panelDatabaseConnection_textFieldUsername_textFieldHeight;
    }

    public int getTextField_panelDatabaseConnection_textFieldUsername_textFieldX() {
        return textField_panelDatabaseConnection_textFieldUsername_textFieldX;
    }

    public int getTextField_panelDatabaseConnection_textFieldUsername_textFieldY() {
        return textField_panelDatabaseConnection_textFieldUsername_textFieldY;
    }

    public int getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldWidth() {
        return textField_panelDatabaseConnection_textFieldDatabaseName_textFieldWidth;
    }

    public int getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldHeight() {
        return textField_panelDatabaseConnection_textFieldDatabaseName_textFieldHeight;
    }

    public int getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldX() {
        return textField_panelDatabaseConnection_textFieldDatabaseName_textFieldX;
    }

    public int getTextField_panelDatabaseConnection_textFieldDatabaseName_textFieldY() {
        return textField_panelDatabaseConnection_textFieldDatabaseName_textFieldY;
    }

    public int getButton_panelDatabaseConnection_buttonConnect_buttonWidth() {
        return button_panelDatabaseConnection_buttonConnect_buttonWidth;
    }

    public int getButton_panelDatabaseConnection_buttonConnect_buttonHeight() {
        return button_panelDatabaseConnection_buttonConnect_buttonHeight;
    }

    public int getButton_panelDatabaseConnection_buttonConnect_buttonX() {
        return button_panelDatabaseConnection_buttonConnect_buttonX;
    }

    public int getButton_panelDatabaseConnection_buttonConnect_buttonY() {
        return button_panelDatabaseConnection_buttonConnect_buttonY;
    }

    public int getTextField_panelDatabaseConnection_textFieldPort_textFieldWidth() {
        return textField_panelDatabaseConnection_textFieldPort_textFieldWidth;
    }

    public int getTextField_panelDatabaseConnection_textFieldPort_textFieldHeight() {
        return textField_panelDatabaseConnection_textFieldPort_textFieldHeight;
    }

    public int getTextField_panelDatabaseConnection_textFieldPort_textFieldX() {
        return textField_panelDatabaseConnection_textFieldPort_textFieldX;
    }

    public int getTextField_panelDatabaseConnection_textFieldPort_textFieldY() {
        return textField_panelDatabaseConnection_textFieldPort_textFieldY;
    }

    public int getTextField_panelDatabaseConnection_textFieldHostname_textFieldWidth() {
        return textField_panelDatabaseConnection_textFieldHostname_textFieldWidth;
    }

    public int getTextField_panelDatabaseConnection_textFieldHostname_textFieldHeight() {
        return textField_panelDatabaseConnection_textFieldHostname_textFieldHeight;
    }

    public int getTextField_panelDatabaseConnection_textFieldHostname_textFieldX() {
        return textField_panelDatabaseConnection_textFieldHostname_textFieldX;
    }

    public int getTextField_panelDatabaseConnection_textFieldHostname_textFieldY() {
        return textField_panelDatabaseConnection_textFieldHostname_textFieldY;
    }
    public int getFrame_view_mainFrame_frameHeight() {
        return frame_view_mainFrame_frameHeight;
    }

    public int getFrame_view_mainFrame_frameWidth() {
        return frame_view_mainFrame_frameWidth;
    }

    public int getPanel_view_panelDatabaseConnection_panelWidth() {
        return panel_view_panelDatabaseConnection_panelWidth;
    }

    public int getPanel_view_panelDatabaseConnection_panelHeight() {
        return panel_view_panelDatabaseConnection_panelHeight;
    }

    public int getPanel_view_panelDatabaseConnection_panelX() {
        return panel_view_panelDatabaseConnection_panelX;
    }

    public int getPanel_view_panelDatabaseConnection_panelY() {
        return panel_view_panelDatabaseConnection_panelY;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public Dimension getSize() {
        return size;
    }
}
