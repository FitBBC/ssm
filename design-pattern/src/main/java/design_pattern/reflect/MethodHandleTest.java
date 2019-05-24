package design_pattern.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author fitbbc
 * @date 2019/05/23
 */
public class MethodHandleTest {



    public MethodHandle getMethodHandle(){
        MethodHandle methodHandle = null;
        //返回类型
        //参数类型
        MethodType methodType = MethodType.methodType(void.class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        try {
            //目标类
            //目标方法
            //方法类型
            methodHandle = lookup.findVirtual(MethodHandleTest.class, "print", methodType);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return methodHandle;
    }


    public void print(){
        System.out.println("methodHandle test");
    }

    public static void main(String[] args) throws Throwable {
        MethodHandleTest methodHandleTest = new MethodHandleTest();
        MethodHandle methodHandle = methodHandleTest.getMethodHandle();

        //Object result = methodHandle.invoke(methodHandleTest);
        methodHandle.invokeExact(methodHandleTest);

        System.out.println();
    }
}
