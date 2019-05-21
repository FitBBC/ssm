package com.bbc.spring.beanfactory;

import org.dom4j.Document;
import org.dom4j.Element;

import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class XmlBeanDefinitionDocumentParse {
    private DefaultListableBeanFactory beanFactory;
    public void loadBeanDefinition(DefaultListableBeanFactory defaultListableBeanFactory, Document document) {
        this.beanFactory = defaultListableBeanFactory;
        Element rootElement = document.getRootElement();
        parseRootElement(rootElement);
    }

    /**
     * 解析根标签beans
     * @param rootElement
     */
    private void parseRootElement(Element rootElement) {
        if (rootElement == null)
            return;
        List<Element> elementList = rootElement.elements();
        for (Element element : elementList){
            //解析bean
            parseBeanElement(element);
        }
    }

    /**
     * 解析bean标签
     * @param element
     */
    private void parseBeanElement(Element element) {
        String id = element.attributeValue("id");
        String clazz = element.attributeValue("class");
        String initMethod = element.attributeValue("init-method");

        String beanName = id;
        //创建beanDefinition信息,根据id和class,设置初始化方法
        BeanDefinition beanDefinition = new BeanDefinition(beanName, clazz);
        beanDefinition.setInitMethod(initMethod);
        List<Element> propertyElements = element.elements("property");
        for (Element propertyElement: propertyElements){
            //解析property标签
            parsePropertyElement(propertyElement, beanDefinition);
        }
        //注册beanDefinition
        registerBeanDefinition(beanName, beanDefinition);
    }

    /**
     * 注册beanDefinition
     * @param beanName
     * @param beanDefinition
     */
    private void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanName,beanDefinition);
    }

    /**
     * 解析property标签
     * @param propertyElement
     * @param beanDefinition
     */
    private void parsePropertyElement(Element propertyElement, BeanDefinition beanDefinition) {
        String name = propertyElement.attributeValue("name");
        String value = propertyElement.attributeValue("value");
        String ref = propertyElement.attributeValue("ref");
        // value 和ref 不能同时设置
        if (value != null && ref != null){
            return;
        }
        //定义property对象
        PropertyValue propertyValue = null;
        if (value != null){
            //value不为空,把string类型的value和目标类型存储到对象中,赋值给property.注册到beanDefinition中
            TypedStringValue typedStringValue = new TypedStringValue(value);
            Class<?> typeClass = ReflectUtils.getTypeByFiledName(beanDefinition.getBeanClassName(), name);
            typedStringValue.setTargetType(typeClass);
            //初始化property对象
            propertyValue = new PropertyValue(name, typedStringValue);
            //注册到beanDefinition中
            beanDefinition.addPropertyValue(propertyValue);
        }else if (ref != null){
            //引用类型,把引用类型的ref值存储到对象中,赋值给property.注册到beanDefinition中
            RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference(ref);
            propertyValue = new PropertyValue(name, runtimeBeanReference);
            beanDefinition.addPropertyValue(propertyValue);
        }else {
            return;
        }
    }
}
