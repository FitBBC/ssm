package design_pattern.proxy.dynamic_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * 主要作用就是生成代理类 使用CGLib动态代理技术实现 它是基于继承的方式实现的
 * @author fitbbc
 * @date 2019/05/23
 */
public class CgLibProxyFactory implements MethodInterceptor {


    private Object target;

    public CgLibProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxy(){
        // 创建增强器
        Enhancer enhancer = new Enhancer();
        // 设置需要增强的类的类对象
        enhancer.setSuperclass(target.getClass());
        // 设置回调函数
        enhancer.setCallback(this);
        // 获取增强之后的代理对象
        return enhancer.create();
    }


    /***
     * Object proxy:这是代理对象，也就是[目标对象]的子类
     * Method method:[目标对象]的方法
     * Object[] arg:参数
     * MethodProxy methodProxy：代理对象的方法
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("before proxy");

        // 该行代码，实际调用的是[目标对象]的方法
        Object result = method.invoke(target, objects);

        // 通过调用子类[代理类]的invokeSuper方法，去实际调用[目标对象]的方法
        //Object result = methodProxy.invokeSuper(proxy, objects);
        // 代理对象调用代理对象的invokeSuper方法，而invokeSuper方法会去调用目标类的invoke方法完成目标对象的调用


        System.out.println("after proxy");
        return result;
    }
}
