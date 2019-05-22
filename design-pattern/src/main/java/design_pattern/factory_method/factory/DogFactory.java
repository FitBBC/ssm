package design_pattern.factory_method.factory;

import design_pattern.factory_method.product.Dog;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class DogFactory implements AnimalFactory {
    public Dog getAnimal() {
        return new Dog();
    }
}
