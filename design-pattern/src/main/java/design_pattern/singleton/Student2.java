package design_pattern.singleton;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class Student2 {
    private static Student2 ourInstance;

    public static synchronized Student2 getInstance() {
        if (ourInstance == null)
            ourInstance = new Student2();
        return ourInstance;
    }

    private Student2() {
    }
}
