package design_pattern.prototype;

import java.io.IOException;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Prototype prototype = new Prototype();
        prototype.setName("zhangsan");
        prototype.setStudent(new Student("lisi"));

        System.out.println("克隆之前的对象" + prototype.getName());
        System.out.println("克隆之前的对象" + prototype.getStudent());


        Prototype  prototype1 = (Prototype) prototype.clone();
        System.out.println("浅克隆之后的对象" + prototype1.getName());
        System.out.println("浅克隆之后的对象" + prototype1.getStudent());


        Prototype  prototype2 = (Prototype) prototype.deepClone();
        System.out.println("深克隆之后的对象" + prototype2.getName());
        System.out.println("深克隆之后的对象" + prototype2.getStudent());
    }
}
