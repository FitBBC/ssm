package design_pattern.singleton;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class Student1 {
    private static Student1 ourInstance;

    public static Student1 getInstance() {
        if (ourInstance == null)
            ourInstance = new Student1();
        return ourInstance;
    }

    private Student1() {
    }
}
