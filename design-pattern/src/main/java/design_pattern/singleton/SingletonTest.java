package design_pattern.singleton;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class SingletonTest {

    public static void main(String[] args) {
        System.out.println(Student.getInstance());
        System.out.println(Student1.getInstance());
        System.out.println(Student2.getInstance());
        System.out.println(Student3.getInstance());
        System.out.println(Student4.getInstance());
    }
}
