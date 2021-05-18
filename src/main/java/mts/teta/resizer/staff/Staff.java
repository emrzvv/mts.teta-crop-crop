package mts.teta.resizer.staff;

public class Staff {
    public static final String description = "Available formats: jpeg png webp\n" +
            "Usage: convert input-file [options ...] output-file";

    public static final String[] argsTest1 = {"C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\Good_Will_Hunting_1997.jpg", "C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\new_test.jpg"};
    public static final String[] argsTest2 = {"C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\Good_Will_Hunting_1997.jpg", "--quality=50" ,"C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\new_test.jpg"};
    public static final String[] argsTest3 = {"C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\Good_Will_Hunting_1997.jpg", "--quality=50" , "--resize", "100", "200", "C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\new_test.jpg"};
    public static final String[] argsTest4 = {"C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\Good_Will_Hunting_1997.jpg", "--resize", "100", "200", "C:\\Users\\egor\\Documents\\GitHub\\mts.teta-crop-crop\\src\\test\\resources\\new_test.jpg"};
}
