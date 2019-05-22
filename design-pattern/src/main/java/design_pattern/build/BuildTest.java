package design_pattern.build;

import design_pattern.build.builder.StudentBuilder;
import design_pattern.build.product.Student;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class BuildTest {
    public static void main(String[] args) {
        StudentBuilder studentBuilder = new StudentBuilder(1);
        Student student = studentBuilder.name("zhangsan").age(18).father("lisi").build();
        System.out.println(student.toString());
    }
}
