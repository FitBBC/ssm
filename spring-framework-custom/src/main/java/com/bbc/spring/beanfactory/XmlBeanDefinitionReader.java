package com.bbc.spring.beanfactory;

import org.dom4j.Document;

import java.io.InputStream;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class XmlBeanDefinitionReader {

    public void loadBeanDefinition(DefaultListableBeanFactory defaultListableBeanFactory, InputStream inputStream) {
        DocumentReader documentReader = new DocumentReader();
        Document document = documentReader.createDocument(inputStream);

        XmlBeanDefinitionDocumentParse xmlBeanDefinitionDocumentParse = new XmlBeanDefinitionDocumentParse();
        xmlBeanDefinitionDocumentParse.loadBeanDefinition(defaultListableBeanFactory, document);

    }
}
