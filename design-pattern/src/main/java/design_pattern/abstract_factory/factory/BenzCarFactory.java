package design_pattern.abstract_factory.factory;

import design_pattern.abstract_factory.product.BenzMpv;
import design_pattern.abstract_factory.product.BenzSuv;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class BenzCarFactory implements CarFactory {
    public BenzMpv getMpv() {
        return new BenzMpv();
    }

    public BenzSuv getSuv() {
        return new BenzSuv();
    }
}
