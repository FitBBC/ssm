package com.bbc.spring.beanfactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class DefaultListableBeanFactory extends AbstractBeanFactory {

    //bean 实例集合,单例饿汗式
    private Map<String, Object> beanInstances = new HashMap<String, Object>();

    //解析之后的beanDefinition信息集合
    private Map<String, BeanDefinition> beanDefinitions = new HashMap<String, BeanDefinition>();

    // 解析xml文件的resource集合,classpath,url,file
    private List<Resource> resources = new ArrayList<Resource>();

    // 简单类型转换convert集合
    private List<TypeConverter> typeConverters = new ArrayList<TypeConverter>();

    public DefaultListableBeanFactory(String location) {
        resources.add(new ClassPathResource(location));
        typeConverters.add(new StringTypeConverter());
        typeConverters.add(new IntegerTypeConverter());

        // 初始化beanFactory -- 加载和注册beanDefinition信息
        // 读取xml, 加载beanDefinition
        // 将beanDefinition注册到beanFactory
        InputStream inputStream = getInputStream();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader();
        xmlBeanDefinitionReader.loadBeanDefinition(this, inputStream);

    }

    private InputStream getInputStream() {
        for (Resource resource : resources) {
            if (resource.isHandleLocation()) {
                return resource.getInputStream();
            }
        }
        return null;
    }

    public Object getBean(String name) {
        //先判断是否已经包含bean实例,如果没有再创建
        Object beanInstance = beanInstances.get(name);
        if (beanInstance != null) {
            return beanInstance;
        }
        // 1.创建实例
        //      先获取beanDefinition信息,没有返回null
        //      通过反射,直接根据字符串class信息, 创建对应的实例
        BeanDefinition beanDefinition = this.beanDefinitions.get(name);
        if (beanDefinition == null) {
            return null;
        }
        beanInstance = createBeanInstance(beanDefinition.getBeanClassName());

        // 2.依赖注入
        //      xml 中配置都是String,需要有引用类型和简单类型的区别
        //      获取属性(多个), 依次注入
        //          不同的属性类型, 对不同的value/ref值进行不同处理
        //          简单类型可以直接类型转换注入
        //          引用类型需要通过ref值从ioc容器获取对应的bean实例
        setProperty(beanInstance, beanDefinition.getPropertyValueList());

        // 3.对象初始化
        //      获取bean标签的init-method方法,执行
        invokeMethod(beanInstance, beanDefinition.getInitMethod());

        // 将bean实例放入缓存map中
        beanInstances.put(name, beanInstance);
        return beanInstance;
    }

    /**
     * 执行bean实例初始化方法
     *
     * @param beanInstance
     * @param initMethod
     */
    private void invokeMethod(Object beanInstance, String initMethod) {
        if (initMethod != null && !initMethod.equals("")) {
            //通过反射,执行初始化方法
            ReflectUtils.invokeMethod(beanInstance, initMethod);
        }
    }

    /**
     * 依赖注入,通过property信息给bean实例赋值
     *
     * @param beanInstance
     * @param propertyValueList
     */
    private void setProperty(Object beanInstance, List<PropertyValue> propertyValueList) {
        for (PropertyValue propertyValue : propertyValueList) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            Object valueToUse = resolveValue(value);
            //通过反射给bean实例赋值
            ReflectUtils.setProperty(beanInstance, name, valueToUse);
        }

    }

    /**
     * 解析value,获取真正需要赋值的value
     * @param value
     * @return
     */
    private Object resolveValue(Object value) {
        Object valueToUse = null;
        if (value instanceof TypedStringValue) {
            //简单类型,需要通过String类型转换获取真正的value
            TypedStringValue temp = (TypedStringValue) value;
            //适配器模式
            for (TypeConverter typeConverter : typeConverters) {
                if (typeConverter.isType(temp.getTargetType())) {
                    valueToUse = typeConverter.convert(temp.getValue());
                }
            }
        } else if (value instanceof RuntimeBeanReference) {
            //引用类型通过ioc容器获取实例
            RuntimeBeanReference temp = (RuntimeBeanReference) value;
            valueToUse = this.getBean(temp.getRef());
        } else {
            return null;
        }
        return valueToUse;
    }

    /**
     * 反射根据全路径名获取实例
     * @param beanClassName
     * @return
     */
    private Object createBeanInstance(String beanClassName) {
        return ReflectUtils.createObject(beanClassName);
    }

    /**
     * 注册beanDefinition信息,不能重复注册
     * @param beanName
     * @param beanDefinition
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        if (!beanDefinitions.containsKey(beanName)) {
            beanDefinitions.put(beanName, beanDefinition);
        }
    }
}
