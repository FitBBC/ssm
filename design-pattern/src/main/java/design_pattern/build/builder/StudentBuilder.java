package design_pattern.build.builder;

import design_pattern.build.product.Father;
import design_pattern.build.product.Student;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class StudentBuilder {
    private Student student = new Student();

    public StudentBuilder(Integer id) {
        student.setId(id);
    }

    public StudentBuilder name(String name){
        student.setName(name);
        return this;
    }

    public StudentBuilder age(int age) {
        student.setAge(age);
        return this;
    }

    public StudentBuilder father(String fatherName) {
        Father father = new Father();
        father.setName(fatherName);
        student.setFather(father);
        return this;
    }

    public Student build(){
        return student;
    }
}
