package view;

import java.awt.Color;

public class Colorscheme {
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

        Color sqlColor = new Color(sqlR, sqlG, sqlB);
        Color mariaColor = new Color(mariaR, mariaG, mariaB);
        Color PostColor = new Color(PostR, PostG, PostB);
        Color StandardColor = new Color(StandardR, StandardG, StandardB);


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

    }
