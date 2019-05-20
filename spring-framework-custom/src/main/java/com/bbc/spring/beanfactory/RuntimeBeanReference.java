package com.bbc.spring.beanfactory;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class RuntimeBeanReference {

    private String ref;

    public RuntimeBeanReference(String ref) {
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}
