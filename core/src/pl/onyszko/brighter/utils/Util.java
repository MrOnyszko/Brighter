package pl.onyszko.brighter.utils;

import com.badlogic.gdx.graphics.Color;

/**
 * Created on 23.02.2016
 *
 * @author Sławomir Onyszko
 */
public class Util {

    /**
     * Method that return a Color object with specified values.
     *
     * @param r - red.
     * @param g - green
     * @param b - blue
     * @param a - alpha
     * @return - {@link Color}.
     */
    public static Color rgba(float r, float g, float b, float a) {
        return new Color(r / 255f, g / 255f, b / 255f, a);
    }

    /**
     * Method that return a Color object with specified hex string.
     *
     * @param hex - string eg. "#ccc" or "ccc".
     * @return - {@link Color}.
     */
    public static Color hexToRGBA(String hex) {
        hex = hex.replace("#", "");
        return new Color(
                Integer.parseInt(hex.substring(0, 2), 16) / 255f,
                Integer.parseInt(hex.substring(2, 4), 16) / 255f,
                Integer.parseInt(hex.substring(4, 6), 16) / 255f,
                Integer.parseInt(hex.substring(6, 8), 16) / 100f
        );

    }

    /**
     * Method that return a Color object with specified hex string.
     *
     * @param hex - string eg. "#ccc" or "ccc".
     * @return - {@link Color}.
     */
    public static Color hex(long hex) {

        float a = (hex & 0xFF000000L) >> 24;
        float r = (hex & 0xFF000L) >> 16;
        float g = (hex & 0xFF00L) >> 8;
        float b = (hex & 0xFFL);

        return new Color(r / 255f, g / 255, b / 255, a / 255);
    }

}
