package mts.teta.resizer.imageprocessor;

import marvin.image.MarvinImage;
import marvin.image.MarvinImageMask;
import marvinplugins.MarvinPluginCollection;
import mts.teta.resizer.ResizerApp;
import org.marvinproject.image.blur.gaussianBlur.GaussianBlur;
import org.marvinproject.image.transform.scale.Scale;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {
    private BufferedImage originalImage;

    public ImageProcessor() {}

    private void changeQuality(BufferedImage inputImage, double newQuality) {

    }

    private void changeImageSize(Integer w, Integer h) {
        MarvinImage image = new MarvinImage(originalImage);
        MarvinPluginCollection.scale(image.clone(), image, w, h);
        originalImage = image.getBufferedImageNoAlpha();
    }

    private void cropImage(Integer x, Integer y, Integer deltaX, Integer deltaY) {
        originalImage = originalImage.getSubimage(x, y, deltaX, deltaY);
    }

    private void blurImage(Integer r) {
        MarvinImage image = new MarvinImage(originalImage);
        MarvinPluginCollection.gaussianBlur(image.clone(), image, r, MarvinImageMask.NULL_MASK);
        originalImage = image.getBufferedImageNoAlpha();
    }

    public void processImage(BufferedImage inputImage, ResizerApp resizerApp) throws IOException {
        originalImage = inputImage;

        for (String arg : resizerApp.arguments) {
            if (arg.equals("resize")) {
                changeImageSize(resizerApp.width, resizerApp.height);
            }
            else if (arg.equals("quality")) {
                continue;
            }
            else if (arg.equals("crop")) {
                cropImage(resizerApp.cropX, resizerApp.cropY, resizerApp.cropDeltaX, resizerApp.cropDeltaY);
            }
            else if (arg.equals("blur")) {
                blurImage(resizerApp.blurRadius);
            }
        }

        ImageIO.write(originalImage, resizerApp.imageFormat, new File(resizerApp.outputFile.toString()));
    }
}
