package design_pattern.abstract_factory.factory;

import design_pattern.abstract_factory.product.MPV;
import design_pattern.abstract_factory.product.SUV;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public interface CarFactory {

    MPV getMpv();

    SUV getSuv();
}
