package design_pattern.singleton;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class Student4 {

    private static class StudentInstance{
        private static Student4 student4 = new Student4();
    }

    public static Student4 getInstance() {
        return StudentInstance.student4;
    }

    private Student4() {
    }
}
