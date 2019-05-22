package design_pattern.decorate;

/**
 * @author fitbbc
 * @date 2019/05/22
 */
public class DecorateTest {

    public static void main(String[] args) {
        PhoneInterface phoneInterface = new IPhone();
        phoneInterface.call();



        PhoneInterface phoneInterface1 = new IPhoneDecorate(phoneInterface);
        phoneInterface1.call();
    }
}
