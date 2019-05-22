package design_pattern.prototype;

import java.io.*;

/**
 * 原型模式 理解克隆的作用：在原对象的基础上，完全复制一个新的对象（属性都是新的）
 * 浅复制：新对象的简单类型和String类型可以复制为新的，但是引用对象还是喝原对象的一样。 深复制：完全复制一个新的对象（属性都是新的）
 *
 * @author fitbbc
 * @date 2019/05/22
 */
public class Prototype implements Cloneable, Serializable {

    private String name;

    private Student student;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    //浅复制
    public Object clone() throws CloneNotSupportedException {
        // super.clone()其实就是调用了Object对象的clone方法
        // Object对象的clone方法是调用了native方法去在JVM中实现对象复制。
        Prototype proto = (Prototype) super.clone();
        return proto;
    }

    //深复制
    //要实现深复制，需要采用流的形式读入当前对象的二进制输入，再写出二进制数据对应的对象。
    public Object deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }
}
