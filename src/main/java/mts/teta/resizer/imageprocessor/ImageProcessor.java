package mts.teta.resizer.imageprocessor;

import marvin.image.MarvinImage;
import marvin.image.MarvinImageMask;
import marvinplugins.MarvinPluginCollection;
import mts.teta.resizer.BadAttributesException;
import mts.teta.resizer.ResizerApp;
import mts.teta.resizer.staff.Staff;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {
    private BufferedImage originalImage;
    private File inputFile;
    private File outputFile;
    private MarvinImage mImage;
    private MarvinImage mImageCloned;

    public ImageProcessor() {}

    private void changeQuality(int newQuality, File from, File to) throws IOException {
        Thumbnails.of(from)
                .scale(1)
                .outputQuality((double) newQuality / 100.0)
                .allowOverwrite(true)
                .toFile(to);
    }

    private void reInitMarvinImages() {
        mImage = new MarvinImage(originalImage);
        mImageCloned = new MarvinImage(originalImage);
    }

    private void changeImageSize(Integer w, Integer h) {
        MarvinPluginCollection.scale(mImageCloned, mImage, w, h);
        originalImage = mImage.getBufferedImageNoAlpha();
    }

    private void cropImage(Integer w, Integer h, Integer x, Integer y) {
        MarvinPluginCollection.crop(mImageCloned, mImage, x, y, w, h);
        originalImage = mImage.getBufferedImageNoAlpha();
    }

    private void blurImage(Integer r) {
        MarvinPluginCollection.gaussianBlur(mImageCloned, mImage, r, MarvinImageMask.NULL_MASK);
        originalImage = mImage.getBufferedImageNoAlpha();
    }

    public void processImage(File inputFile, ResizerApp resizerApp) throws IOException, BadAttributesException {
        this.inputFile = inputFile;

        if (!this.inputFile.exists() || !Validators.validFormat(resizerApp.imageFormat)) {
            throw new IIOException(Staff.inputFileExceptionMessage);
        }

        originalImage = ImageIO.read(inputFile);

        if (resizerApp.arguments.contains(Staff.Operation.RESIZE)) {
            if (!Validators.validResizeData(new int[]{resizerApp.resizeWidth, resizerApp.resizeHeight})) {
                throw new BadAttributesException(Staff.defaultExceptionMessage);
            }
            reInitMarvinImages();
            changeImageSize(resizerApp.resizeWidth, resizerApp.resizeHeight);

        }

        if (resizerApp.arguments.contains(Staff.Operation.CROP)) {
            if (!Validators.validCropData(new int[]{resizerApp.cropWidth, resizerApp.cropHeight, resizerApp.cropX, resizerApp.cropY}, originalImage)) {
                throw new BadAttributesException(Staff.defaultExceptionMessage);
            }
            reInitMarvinImages();
            cropImage(resizerApp.cropWidth, resizerApp.cropHeight, resizerApp.cropX, resizerApp.cropY);
        }

        if (resizerApp.arguments.contains(Staff.Operation.BLUR)) {
            if (!Validators.validInteger(resizerApp.blurRadius, 1, 10)) {
                throw new BadAttributesException(Staff.defaultExceptionMessage);
            }
            reInitMarvinImages();
            blurImage(resizerApp.blurRadius);
        }

        if (resizerApp.arguments.contains(Staff.Operation.FORMAT)) {
            if (!Validators.validFormat(resizerApp.newFormat)){
                throw new BadAttributesException(Staff.defaultExceptionMessage);
            }
            resizerApp.imageFormat = resizerApp.newFormat;
        }

        this.outputFile = new File(resizerApp.outputFile.toString());
        ImageIO.write(originalImage, resizerApp.imageFormat, outputFile);

        if (resizerApp.arguments.contains(Staff.Operation.QUALITY)) {
            if (!Validators.validInteger(resizerApp.quality, 1, 100)) {
                throw new BadAttributesException(Staff.defaultExceptionMessage);
            }
            changeQuality(resizerApp.quality, outputFile, outputFile);
        }
    }
}
