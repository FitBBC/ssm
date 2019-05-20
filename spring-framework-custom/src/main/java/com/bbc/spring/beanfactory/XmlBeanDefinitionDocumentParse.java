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

    private void parseRootElement(Element rootElement) {
        if (rootElement == null)
            return;
        List<Element> elementList = rootElement.elements();
        for (Element element : elementList){
            parseBeanElement(element);
        }
    }

    private void parseBeanElement(Element element) {
        String id = element.attributeValue("id");
        String clazz = element.attributeValue("class");
        String initMethod = element.attributeValue("init-method");

        String beanName = id;
        BeanDefinition beanDefinition = new BeanDefinition(beanName, clazz);
        beanDefinition.setInitMethod(initMethod);
        List<Element> propertyElements = element.elements("property");
        for (Element propertyElement: propertyElements){
            parsePropertyElement(propertyElement, beanDefinition);
        }

        registerBeanDefinition(beanName, beanDefinition);
    }

    private void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanFactory.registerBeanDefinition(beanName,beanDefinition);
    }

    private void parsePropertyElement(Element propertyElement, BeanDefinition beanDefinition) {
        String name = propertyElement.attributeValue("name");
        String value = propertyElement.attributeValue("value");
        String ref = propertyElement.attributeValue("ref");
        if (value != null && ref != null){
            return;
        }
        PropertyValue propertyValue = null;
        if (value != null){
            TypedStringValue typedStringValue = new TypedStringValue(value);
            Class<?> typeClass = ReflectUtils.getTypeByFiledName(beanDefinition.getBeanClassName(), name);
            typedStringValue.setTargetType(typeClass);
            propertyValue = new PropertyValue(name, typedStringValue);
            beanDefinition.addPropertyValue(propertyValue);
        }else if (ref != null){
            RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference(ref);
            propertyValue = new PropertyValue(name, runtimeBeanReference);
            beanDefinition.addPropertyValue(propertyValue);
        }else {
            return;
        }
    }
}
