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

    // Checkboxes

        // MySQL Checkbox
        /*int checkBox_panelDatabaseConnection_checkBoxMySql_checkBoxX = (screenWidth/2) + 150;
        int checkBox_panelDatabaseConnection_checkBoxMySql_checkBoxY = 150;
        int checkBox_panelDatabaseConnection_checkBoxMySql_checkBoxWidth = 74;
        int checkBox_panelDatabaseConnection_checkBoxMySql_checkBoxHeight = 20;
        String checkBox_panelDatabaseConnection_checkBoxMySql_checkBoxLabel = "MySQL";*/

        // MariaDB Checkbox
        /*int checkBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxX = (screenWidth/2) + 50;
        int checkBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxY = 150;
        int checkBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxWidth = 83;
        int checkBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxHeight = 20;
        String checkBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxLabel = "MariaDB";*/

        // PostgreSQL Checkbox
        /*int checkBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxX = (screenWidth/2) - 100;
        int checkBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxY = 150;
        int checkBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxWidth = 90;
        int checkBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxHeight = 20;
        String checkBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxLabel = "PostgreSQL";*/

    // RadioButtons
        // MySQL RadioButton
        int radioButton_panelDatabaseConnection_radioButtonMySql_radioButtonX = (screenWidth/2) + 150;
        int radioButton_panelDatabaseConnection_radioButtonMySql_radioButtonY = 150;
        int radioButton_panelDatabaseConnection_radioButtonMySql_radioButtonWidth = 74;
        int radioButton_panelDatabaseConnection_radioButtonMySql_radioButtonHeight = 20;
        String radioButton_panelDatabaseConnection_radioButtonMySql_radioButtonLabel = "MySQL";

        // MariaDB RadioButton
        int radioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonX = (screenWidth/2) + 50;
        int radioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonY = 150;
        int radioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonWidth = 83;
        int radioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonHeight = 20;
        String radioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonLabel = "MariaDB";

        // PostgreSQL RadioButton
        int radioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonX = (screenWidth/2) -50;
        int radioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonY = 150;
        int radioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonWidth = 105;
        int radioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonHeight = 20;
        String radioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonLabel = "PostgreSQL";

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
    int textField_panelDatabaseConnection_textFieldPort_textFieldY = 270;
     //Database name
    int textField_panelDatabaseConnection_textFieldDatabaseName_textFieldWidth = 300;
    int textField_panelDatabaseConnection_textFieldDatabaseName_textFieldHeight = 50;
    int textField_panelDatabaseConnection_textFieldDatabaseName_textFieldX = 200;
    int textField_panelDatabaseConnection_textFieldDatabaseName_textFieldY = 330;
     //Username
    int textField_panelDatabaseConnection_textFieldUsername_textFieldWidth = 300;
    int textField_panelDatabaseConnection_textFieldUsername_textFieldHeight = 50;
    int textField_panelDatabaseConnection_textFieldUsername_textFieldX = 200;
    int textField_panelDatabaseConnection_textFieldUsername_textFieldY = 390;
        //Password
    int textField_panelDatabaseConnection_textFieldPassword_textFieldWidth = 300;
    int textField_panelDatabaseConnection_textFieldPassword_textFieldHeight = 50;
    int textField_panelDatabaseConnection_textFieldPassword_textFieldX = 200;
    int textField_panelDatabaseConnection_textFieldPassword_textFieldY = 450;

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
    // Checkboxes
        // MySQL Checkbox
        /*public int getCheckBox_panelDatabaseConnection_checkBoxMySql_checkBoxX() {
            return checkBox_panelDatabaseConnection_checkBoxMySql_checkBoxX;
        }
        public int getCheckBox_panelDatabaseConnection_checkBoxMySql_checkBoxY() {
            return checkBox_panelDatabaseConnection_checkBoxMySql_checkBoxY;
        }
        public int getCheckBox_panelDatabaseConnection_checkBoxMySql_checkBoxWidth() {
            return checkBox_panelDatabaseConnection_checkBoxMySql_checkBoxWidth;
        }
        public int getCheckBox_panelDatabaseConnection_checkBoxMySql_checkBoxHeight() {
            return checkBox_panelDatabaseConnection_checkBoxMySql_checkBoxHeight;
        }
        public String getCheckBox_panelDatabaseConnection_checkBoxMySql_checkBoxLabel() {
            return checkBox_panelDatabaseConnection_checkBoxMySql_checkBoxLabel;
        }*/

        // MariaDB Checkbox
        /*public int getCheckBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxX() {
            return checkBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxX;
        }
        public int getCheckBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxY() {
            return checkBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxY;
        }
        public int getCheckBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxWidth() {
            return checkBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxWidth;
        }
        public int getCheckBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxHeight() {
            return checkBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxHeight;
        }
        public String getCheckBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxLabel() {
            return checkBox_panelDatabaseConnection_checkBoxMariaDB_checkBoxLabel;
        }*/

        // PostgreSQL
        /*public int getCheckBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxX() {
            return checkBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxX;
        }
        public int getCheckBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxY() {
            return checkBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxY;
        }
        public int getCheckBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxWidth() {
            return checkBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxWidth;
        }
        public int getCheckBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxHeight() {
            return checkBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxHeight;
        }
        public String getCheckBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxLabel() {
            return checkBox_panelDatabaseConnection_checkBoxPostgreSQL_checkBoxLabel;
        }*/

    // RadioButtons
        // MySQL
        public int getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonX() {
            return radioButton_panelDatabaseConnection_radioButtonMySql_radioButtonX;
        }
        public int getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonY() {
            return radioButton_panelDatabaseConnection_radioButtonMySql_radioButtonY;
        }
        public int getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonWidth() {
            return radioButton_panelDatabaseConnection_radioButtonMySql_radioButtonWidth;
        }
        public int getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonHeight() {
            return radioButton_panelDatabaseConnection_radioButtonMySql_radioButtonHeight;
        }
        public String getRadioButton_panelDatabaseConnection_radioButtonMySql_radioButtonLabel() {
            return radioButton_panelDatabaseConnection_radioButtonMySql_radioButtonLabel;
        }

        // MariaDB
        public int getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonX() {
            return radioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonX;
        }
        public int getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonY() {
            return radioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonY;
        }
        public int getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonWidth() {
            return radioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonWidth;
        }
        public int getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonHeight() {
            return radioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonHeight;
        }
        public String getRadioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonLabel() {
            return radioButton_panelDatabaseConnection_radioButtonMariaDB_radioButtonLabel;
        }

        // PostgreSQL
        public int getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonX() {
            return radioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonX;
        }
        public int getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonY() {
            return radioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonY;
        }
        public int getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonWidth() {
            return radioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonWidth;
        }
        public int getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonHeight() {
            return radioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonHeight;
        }
        public String getRadioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonLabel() {
            return radioButton_panelDatabaseConnection_radioButtonPostgreSQL_radioButtonLabel;
        }
    // Buttons
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

    // Textfields
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

    // Frames
    public int getFrame_view_mainFrame_frameHeight() {
        return frame_view_mainFrame_frameHeight;
    }

    public int getFrame_view_mainFrame_frameWidth() {
        return frame_view_mainFrame_frameWidth;
    }

    // Panels
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

    // Screen
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
