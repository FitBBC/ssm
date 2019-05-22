package design_pattern.delegate;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class DelegateTest {

    public static void main(String[] args) {
        Pm pm = new Pm();
        pm.setDelegate(new Dev());
        pm.doSomething();
    }
}
