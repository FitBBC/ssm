package design_pattern.singleton;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class Student {
    private static Student ourInstance = new Student();

    public static Student getInstance() {
        return ourInstance;
    }

    private Student() {
    }
}
