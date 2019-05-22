package design_pattern.singleton;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class Student3 {
    private static Student3 ourInstance;

    public static Student3 getInstance() {
        if (ourInstance == null){
            synchronized (Student3.class){
                if (ourInstance == null){
                    ourInstance = new Student3();
                }
            }
        }

        return ourInstance;
    }

    private Student3() {
    }
}
