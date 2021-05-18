package mts.teta.resizer.console;

import mts.teta.resizer.BadAttributesException;
import mts.teta.resizer.staff.Staff;
import picocli.CommandLine;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class ConsoleAttributes {
    public File inputFile;
    public File outputFile;
    public Integer width, height;
    public Integer quality;
    public Integer cropX, cropY, cropDeltaX, cropDeltaY;
    public Integer blurRadius;
    public String imageFormat;

    public HashSet<String> arguments;

    public ConsoleAttributes() {
        inputFile = null;
        outputFile = null;
        arguments = new HashSet<String>();
    }

    private String getFileFormat(File file) {
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1);
    }

    private boolean validInteger(Integer x, int min, int max) {
        return min <= x && x <= max;
    }

    private boolean validFormat(String format) {
        if (format == null || format.equals("")) return false;
        return format.equals("jpg") || format.equals("jpeg") || format.equals("png");
    }
    
    private boolean validResizeData(Integer[] resizeData) { 
        return resizeData.length == 2 &&
                validInteger(resizeData[0], 1, Integer.MAX_VALUE) &&
                validInteger(resizeData[1], 1, Integer.MAX_VALUE);
    }

    private boolean validCropData(Integer[] cropData) throws IOException {
        if (cropData.length != 4) {
            return false;
        }

        BufferedImage image = ImageIO.read(inputFile);

        int[] toCheck = {image.getHeight(), image.getWidth(), image.getHeight() - cropData[0], image.getWidth() - cropData[1]};

        for (int i = 0; i < 4; ++i) {
            if (!validInteger(cropData[i], 0, toCheck[i])) {
                return false;
            }
        }
        return true;
    }

    @CommandLine.Parameters(index = "0", description = "input-file")
    public void setInputFile(File file) throws IIOException {
        if (!file.exists()) {
            throw new IIOException(Staff.inputFileExceptionMessage);
        }

        inputFile = file;
        imageFormat = getFileFormat(file);

        if (!validFormat(imageFormat)) {
            throw new IIOException(Staff.inputFileExceptionMessage);
        }
    }

    @CommandLine.Parameters(index = "1..*", description = "output-file")
    public void setOutputFile(File file) throws IOException {
        outputFile = file;
    }

    @CommandLine.Option(names = "--resize", arity = "2", description = "resize the image")
    public void resize(Integer[] resizeData) throws BadAttributesException {
        if (!validResizeData(resizeData)) {
            throw new BadAttributesException(Staff.resizeExceptionMessage);
        }
        setResizeWidth(resizeData[0]);
        setResizeHeight(resizeData[1]);
    }

    public void setResizeWidth(Integer reducedPreviewWidth) throws BadAttributesException {
        if (!validInteger(reducedPreviewWidth, 1, Integer.MAX_VALUE)) {
            throw new BadAttributesException(Staff.resizeExceptionMessage);
        }
        width = reducedPreviewWidth;
        arguments.add("resize");
    }

    public void setResizeHeight(Integer reducedPreviewHeight) throws BadAttributesException {
        if (!validInteger(reducedPreviewHeight, 1, Integer.MAX_VALUE)) {
            throw new BadAttributesException(Staff.resizeExceptionMessage);
        }
        height = reducedPreviewHeight;
    }

    @CommandLine.Option(names = "--quality", description = "PEG/PNG compression level")
    public void setQuality(Integer q) throws BadAttributesException {
        if (!validInteger(q, 1, 100)) {
            throw new BadAttributesException(Staff.qualityExceptionMessage);
        }
        quality = q;

        arguments.add("quality");
    }

    @CommandLine.Option(names = "--crop", arity = "4", description = "cut out one rectangular area of the image")
    public void setCrop(Integer[] cropData) throws IOException, BadAttributesException {
        if (!validCropData(cropData)) {
            throw new BadAttributesException(Staff.cropExceptionMessage);
        }
        cropX = cropData[0];
        cropY = cropData[1];
        cropDeltaX = cropData[2];
        cropDeltaY = cropData[3];

        arguments.add("crop");
    }

    @CommandLine.Option(names= "--blur", arity = "1", description = "reduce image noise detail levels")
    public void setBlur(Integer radius) throws BadAttributesException {
        if (!validInteger(radius, 1, 10)) {
            throw new BadAttributesException(Staff.blurExceptionMessage);
        }
        blurRadius = radius;

        arguments.add("blur");
    }

    @CommandLine.Option(names = "--format", arity = "1", description = "the image format type")
    public void setFormat(String format) throws BadAttributesException {
        if (!validFormat(format)) {
            throw new BadAttributesException(Staff.formatExceptionMessage);
        }
        imageFormat = format;

        arguments.add("format");
    }
}