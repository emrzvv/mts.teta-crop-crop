package mts.teta.resizer.imageprocessor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Validators {
    public static boolean validInteger(Integer x, int min, int max) {
        return min <= x && x <= max;
    }

    public static String getFileFormat(File file) {
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1);
    }

    public static boolean validFormat(String format) {
        if (format == null || format.equals("")) return false;
        return format.equals("jpg") || format.equals("jpeg") || format.equals("png");
    }

    public static boolean validResizeData(int[] resizeData) {
        return resizeData.length == 2 &&
                validInteger(resizeData[0], 1, Integer.MAX_VALUE) &&
                validInteger(resizeData[1], 1, Integer.MAX_VALUE);
    }

    public static boolean validCropData(int[] cropData, BufferedImage image) {
        if (cropData.length != 4) {
            return false;
        }

        int[] toCheck = {image.getHeight(), image.getWidth(), image.getHeight() - cropData[0], image.getWidth() - cropData[1]};

        for (int i = 0; i < 4; ++i) {
            if (!validInteger(cropData[i], 0, toCheck[i])) {
                return false;
            }
        }
        return true;
    }
}
