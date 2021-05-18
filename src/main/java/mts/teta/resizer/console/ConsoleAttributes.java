package mts.teta.resizer.console;

import org.graalvm.compiler.options.Option;
import picocli.CommandLine;

import java.io.File;

public class ConsoleAttributes {

    /*
    перенести все поля ^
    под параметрами и опциями сразу написать валидацию получаемых значений.
    можно будет сразу выбрасывать исключения, если что-то не так.
    */
    @CommandLine.Parameters(index = "0", description = "input-file")
    public File inputFile;

    @CommandLine.Parameters(index = "1..*", description = "output-file")
    public File outputFile;

    @CommandLine.Option(names = "--resize", arity = "2", description = "resize the image")
    public Integer[] resizeData;

    @CommandLine.Option(names = "--quality", description = "PEG/PNG compression level")
    public Integer quality;

    public void setInputFile(File file) {
        this.inputFile = file;
    }

    public void setOutputFile(File file) {
        this.outputFile = file;
    }

    public void setResizeWidth(Integer reducedPreviewWidth) {
        this.resizeData[0] = reducedPreviewWidth;
    }

    public void setResizeHeight(Integer reducedPreviewHeight) {
        this.resizeData[1] = reducedPreviewHeight;
    }

    public void setQuality(int q) {
        this.quality = q;
    }
}