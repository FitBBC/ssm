package design_pattern.abstract_factory.factory;

import design_pattern.abstract_factory.product.BmwMpv;
import design_pattern.abstract_factory.product.BmwSuv;
import design_pattern.abstract_factory.product.MPV;
import design_pattern.abstract_factory.product.SUV;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class BmwCarFactory implements CarFactory {
    public BmwMpv getMpv() {
        return new BmwMpv();
    }

    public BmwSuv getSuv() {
        return new BmwSuv();
    }
}
