package mts.teta.resizer;

import mts.teta.resizer.console.ConsoleAttributes;
import mts.teta.resizer.imageprocessor.ImageProcessor;
import mts.teta.resizer.staff.Staff;
import picocli.CommandLine;

import javax.imageio.ImageIO;
import java.util.concurrent.Callable;

// mvn compile
// mvn exec:java -Dexec.mainClass=mts.teta.resizer.ResizerApp

@CommandLine.Command(name = "resizer", mixinStandardHelpOptions = true, version = "resizer 0.0.1", description = Staff.description)
public class ResizerApp extends ConsoleAttributes implements Callable<Integer> {

    public static void main(String... args) {
        int exitCode = runConsole(args);
        System.exit(exitCode);
    }

    protected static int runConsole(String[] args) {
        return new CommandLine(new ResizerApp()).execute(args);
    }

    @Override
    public Integer call() throws Exception {
        ImageProcessor imageProcessor = new ImageProcessor();
        imageProcessor.processImage(inputFile, this);
        return 0;
    }
}
