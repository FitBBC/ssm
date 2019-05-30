package com.bbc.spring.framework.beandefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class BeanDefinition {

    private String beanName;
    private String beanClassName;
    private String initMethod;

    private List<PropertyValue> propertyValueList = new ArrayList();

    public BeanDefinition(String beanName, String beanClassName) {
        this.beanName = beanName;
        this.beanClassName = beanClassName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }
}
