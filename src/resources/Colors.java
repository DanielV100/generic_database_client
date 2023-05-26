package resources;

import java.awt.Color;

/**
 * All colors are defined here.
 * @author Lorenz
 */

public class Colors {
    //RGB SQL
    int sqlR = 255;
    int sqlG = 128;
    int sqlB = 0;
    //RGB Maria
    int mariaR = 0;
    int mariaG = 76;
    int mariaB = 153;
    //RGB Maria
    int PostR = 153;
    int PostG = 153;
    int PostB = 255;
    //RGB Maria
    int StandardR = 17;
    int StandardG = 13;
    int StandardB = 139;
    /**
     * Setting different Colors for selected DBs
     * @author Lorenz
     */
    Color sqlColor = new Color(sqlR, sqlG, sqlB);
    Color mariaColor = new Color(mariaR, mariaG, mariaB);
    Color PostColor = new Color(PostR, PostG, PostB);
    Color StandardColor = new Color(StandardR, StandardG, StandardB);

    //colors of popups
    Color popupMessageError = Color.RED;
    Color popupMessageSuccess = Color.GREEN;
    Color uiStandard = Color.WHITE;

    //colors of table
    Color tableHeaderBackground = new Color(238, 220, 130);
    Color tableGridColor = new Color(211, 211, 211);
    Color selectionTableColor = Color.BLUE;

    public Color getSelectionTableColor() {
        return selectionTableColor;
    }

    public Color getTableGridColor() {
        return tableGridColor;
    }

    public Color getTableHeaderBackground() {
        return tableHeaderBackground;
    }

    public Color getSqlColor() {
        return sqlColor;
    }

    public Color getMariaColor() {
        return mariaColor;
    }

    public Color getPostColor() {
        return PostColor;
    }

    public Color getStandardColor() {
        return StandardColor;
    }
    public Color toColorSTandard() {
        return new Color(StandardR, StandardG, StandardB);
    }

    public Color getPopupMessageError() {
        return popupMessageError;
    }

    public Color getUiStandard() {
        return uiStandard;
    }

    public Color getPopupMessageSuccess() {
        return popupMessageSuccess;
    }
}
