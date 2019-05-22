package design_pattern.simple_factory;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class SimpleFactory2 {

    public Cat getCat(){
        return new Cat();
    }

    public Dog getDog(){
        return new Dog();
    }

    public static void main(String[] args) {

        SimpleFactory2 simpleFactory = new SimpleFactory2();
        Cat cat = simpleFactory.getCat();
        cat.eat();
        Dog dog = simpleFactory.getDog();
        dog.eat();
    }
}
