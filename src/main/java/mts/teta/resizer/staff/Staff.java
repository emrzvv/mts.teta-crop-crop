package mts.teta.resizer.staff;

public class Staff {
    public static final String description = "Available formats: jpeg png webp\n" +
            "Usage: convert input-file [options ...] output-file";

    public static final String inputFileExceptionMessage = "Can't read input file!";
    public static final String resizeExceptionMessage = "Incorrect resize values!";
    public static final String cropExceptionMessage = "Incorrect crop values!";
    public static final String blurExceptionMessage = "Incorrect radius!";
    public static final String formatExceptionMessage = "Incorrect Format!";
    public static final String qualityExceptionMessage = "Please check params!";

    public static final String[] argsTest1 = {"C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\Good_Will_Hunting_1997.jpg", "--resize", "1966", "3000", "C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\Good_Will_Hunting_1997.preview.jpg"};
    public static final String[] argsTest2 = {"C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\Good_Will_Hunting_1997.jpg", "--quality=20" ,"C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\new_test.jpg"};
    public static final String[] argsTest3 = {"C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\Good_Will_Hunting_1997.jpg", "--quality=50" , "--resize", "100", "200", "C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\new_test.jpg"};
    public static final String[] argsTest4 = {"C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\Metallica_Kill_Em_All_1983.jpeg", "--quality=10", "--resize", "700", "700", "C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\new_test.jpg"};
    public static final String[] argsTest5 = {"C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\Metallica_Kill_Em_All_1983.jpeg", "--crop", "30", "30", "200", "100", "--resize", "700", "700", "C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\new_test.jpg"};
    public static final String[] argsTest6 = {"C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\Metallica_Kill_Em_All_1983.jpeg", "--blur", "5", "C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\new_test.jpg"};
}
