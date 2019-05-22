package design_pattern.abstract_factory.factory;

import design_pattern.abstract_factory.product.AudiMpv;
import design_pattern.abstract_factory.product.AudiSuv;
import design_pattern.abstract_factory.product.MPV;
import design_pattern.abstract_factory.product.SUV;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class AudiCarFactory implements CarFactory {
    public AudiMpv getMpv() {
        return new AudiMpv();
    }

    public AudiSuv getSuv() {
        return new AudiSuv();
    }
}
