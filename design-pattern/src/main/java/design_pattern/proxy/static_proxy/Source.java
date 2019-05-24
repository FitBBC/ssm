package design_pattern.proxy.static_proxy;

/**
 * @author fitbbc
 * @date 2019/05/23
 */
public class Source implements Sourceable {
    @Override
    public void method() {
        System.out.println("this a original method");
    }
}
