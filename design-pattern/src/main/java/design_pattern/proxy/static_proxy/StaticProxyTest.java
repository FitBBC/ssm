package design_pattern.proxy.static_proxy;

/**
 * @author fitbbc
 * @date 2019/05/23
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        Sourceable proxy = new Proxy();
        proxy.method();
    }
}
