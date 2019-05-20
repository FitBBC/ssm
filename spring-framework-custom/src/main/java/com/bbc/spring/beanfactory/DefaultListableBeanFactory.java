package com.bbc.spring.beanfactory;

import org.dom4j.Document;

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

    private Map<String, Object> beanInstances = new HashMap<String, Object>();

    //解析之后的beanDefinition信息集合
    private Map<String, BeanDefinition> beanDefinitions = new HashMap<String, BeanDefinition>();

    private List<Resource> resources =  new ArrayList<Resource>();

    public DefaultListableBeanFactory(String location) {
        resources.add(new ClassPathResource(location));
        InputStream inputStream = getInputStream();
        // 初始化beanFactory -- 加载和注册beanDefinition信息
        // 读取xml, 加载beanDefinition
        // 将beanDefinition注册到beanFactory
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader();
        xmlBeanDefinitionReader.loadBeanDefinition(this, inputStream);

    }

    private InputStream getInputStream() {
        for (Resource resource: resources){
            if (resource.isHandleLocation()){
                return resource.getInputStream();
            }
        }
        return null;
    }

    public Object getBean(String name) {
        //先判断是否已经包含bean实例,如果没有再创建
        // 1.创建实例
        //      通过反射,直接根据字符串class信息, 创建对应的实例
        // 2.依赖注入
        //      xml 中配置都是String,需要有引用类型和简单类型的区别
        //      获取属性(多个), 依次注入
        //          不同的属性类型, 对不同的value/ref值进行不同处理
        //          简单类型可以直接类型转换注入
        //          引用类型需要通过ref值从ioc容器获取对应的bean实例
        // 3.对象初始化
        //      获取bean标签的init-method方法,执行
        // 将bean实例放入缓存map中
        Object beanInstance = beanInstances.get(name);
        if (beanInstance != null){
            return beanInstance;
        }
        BeanDefinition beanDefinition = this.beanDefinitions.get(name);
        if (beanDefinition == null){
            return null;
        }
        beanInstance = createBeanInstance(beanDefinition.getBeanClassName());

        setProperty(beanInstance, beanDefinition.getPropertyValueList());
        return null;
    }

    private void setProperty(Object beanInstance, List<PropertyValue> propertyValueList) {
        for (PropertyValue propertyValue: propertyValueList){
            String name = propertyValue.getName();
            Object value = propertyValue.getVlaue();

        }
    }

    private Object createBeanInstance(String beanClassName) {
        ReflectUtils.createObject(beanClassName);
    }

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition){
        beanDefinitions.put(beanName, beanDefinition);
    }
}
