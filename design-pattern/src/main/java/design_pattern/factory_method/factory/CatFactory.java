package design_pattern.factory_method.factory;

import design_pattern.factory_method.product.Cat;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class CatFactory implements AnimalFactory {
    public Cat getAnimal() {
        return new Cat();
    }
}
