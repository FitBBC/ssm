package design_pattern.simple_factory;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class SimpleFactory {

    public Animal getAnimal(String type){
        if (type.equals("cat")){
            return new Cat();
        }else if (type.equals("dog")){
            return new Dog();
        }else{
            return null;
        }
    }

    public static void main(String[] args) {

        SimpleFactory simpleFactory = new SimpleFactory();
        Cat cat = (Cat) simpleFactory.getAnimal("cat");
        cat.eat();
        Dog dog = (Dog) simpleFactory.getAnimal("dog");
        dog.eat();
    }
}
