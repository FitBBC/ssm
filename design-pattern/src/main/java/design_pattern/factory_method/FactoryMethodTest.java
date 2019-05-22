package design_pattern.factory_method;

import design_pattern.factory_method.factory.AnimalFactory;
import design_pattern.factory_method.factory.CatFactory;
import design_pattern.factory_method.factory.DogFactory;
import design_pattern.factory_method.product.Cat;
import design_pattern.factory_method.product.Dog;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        AnimalFactory catFactory = new CatFactory();
        Cat cat = (Cat) catFactory.getAnimal();
        cat.eat();

        AnimalFactory dogFactory = new DogFactory();
        Dog dog = (Dog) dogFactory.getAnimal();
        dog.eat();
    }
}
