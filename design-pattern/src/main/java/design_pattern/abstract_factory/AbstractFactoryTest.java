package design_pattern.abstract_factory;

import design_pattern.abstract_factory.factory.AudiCarFactory;
import design_pattern.abstract_factory.factory.BenzCarFactory;
import design_pattern.abstract_factory.factory.BmwCarFactory;
import design_pattern.abstract_factory.factory.CarFactory;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        CarFactory benzCarFactory = new BenzCarFactory();
        benzCarFactory.getMpv().sayHi();
        benzCarFactory.getSuv().sayHi();


        CarFactory bmwCarFactory = new BmwCarFactory();
        bmwCarFactory.getMpv().sayHi();
        bmwCarFactory.getSuv().sayHi();


        CarFactory audiCarFactory = new AudiCarFactory();
        audiCarFactory.getSuv().sayHi();
        audiCarFactory.getMpv().sayHi();
    }
}
