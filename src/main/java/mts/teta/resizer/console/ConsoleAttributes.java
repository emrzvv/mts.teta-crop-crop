package mts.teta.resizer.console;

import mts.teta.resizer.imageprocessor.Validators;
import mts.teta.resizer.staff.Staff;
import picocli.CommandLine;


import java.io.File;
import java.util.HashSet;

public class ConsoleAttributes {
    public File inputFile;
    public File outputFile;
    public Integer resizeWidth, resizeHeight;
    public Integer quality;
    public Integer cropWidth, cropHeight, cropX, cropY;
    public Integer blurRadius;
    public String imageFormat;
    public String newFormat;

    public HashSet<Staff.Operation> arguments;

    public ConsoleAttributes() {
        inputFile = null;
        outputFile = null;
        arguments = new HashSet<>();
    }

    @CommandLine.Parameters(index = "0", hideParamSyntax = true)
    public void setInputFile(File file) {
        inputFile = file;
        imageFormat = Validators.getFileFormat(file);
    }

    @CommandLine.Parameters(index = "1..*", hideParamSyntax = true)
    public void setOutputFile(File file) {
        outputFile = file;
    }

    @CommandLine.Option(names = "--resize", arity = "2", description = "resize the image", paramLabel = "width height")
    public void resize(Integer[] resizeData) {
        setResizeWidth(resizeData[0]);
        setResizeHeight(resizeData[1]);
    }

    public void setResizeWidth(Integer reducedPreviewWidth) {
        resizeWidth = reducedPreviewWidth;
        arguments.add(Staff.Operation.RESIZE);
    }

    public void setResizeHeight(Integer reducedPreviewHeight) {
        resizeHeight = reducedPreviewHeight;
    }

    @CommandLine.Option(names = "--quality", description = "PEG/PNG compression level", paramLabel = "value")
    public void setQuality(Integer q) {
        quality = q;
        arguments.add(Staff.Operation.QUALITY);
    }

    @CommandLine.Option(names = "--crop", arity = "4", description = "cut out one rectangular area of the image", paramLabel = "width height x y")
    public void setCrop(Integer[] cropData) {
        cropWidth = cropData[0];
        cropHeight = cropData[1];
        cropX = cropData[2];
        cropY = cropData[3];
        arguments.add(Staff.Operation.CROP);
    }

    @CommandLine.Option(names= "--blur", arity = "1", description = "reduce image noise detail levels", paramLabel = "{radius}")
    public void setBlur(Integer radius) {
        blurRadius = radius;
        arguments.add(Staff.Operation.BLUR);
    }

    @CommandLine.Option(names = "--format", arity = "1", description = "the image format type", paramLabel = "\"outputFormat\"")
    public void setFormat(String format) {
        newFormat = format;
        arguments.add(Staff.Operation.FORMAT);
    }
}