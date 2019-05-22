package design_pattern.decorate;

/**
 * 装饰模式
 *
 * 		1：装饰类，需要去实现被装饰类接口
 * 		2：装饰类的本质是对已有的类进行功能增强
 *
 * 特点：
 * 		1：外表看起来是被装饰类的接口表示形式
 * 		2：内在其实使用的是被装饰类本身的功能，只是在此基础之上进行增强。
 *
 * @author fitbbc
 * @date 2019/05/22
 */
public class IPhoneDecorate implements PhoneInterface {
    private PhoneInterface iphone;

    public IPhoneDecorate(PhoneInterface iphone) {
        this.iphone = iphone;
    }

    public void call() {
        iphone.call();
        System.out.println("拍照");
    }

}
