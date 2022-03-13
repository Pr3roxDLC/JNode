package me.pr3.JNode.GUI.Util;

import java.awt.*;

public class RenderUtil {

    public static Color getInvertedGrayScale(Color color){
        final int i = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        return new Color(255 - i, 255 - i,255 - i);
    }

    public static Color getGrayScale(Color color){
        final int i = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
        return new Color(i, i, i);
    }

}
