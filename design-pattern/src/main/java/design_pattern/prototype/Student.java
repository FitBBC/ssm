package design_pattern.prototype;

import java.io.Serializable;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class Student implements Serializable {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
