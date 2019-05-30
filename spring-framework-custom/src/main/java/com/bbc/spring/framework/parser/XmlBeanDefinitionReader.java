package com.bbc.spring.framework.parser;

import com.bbc.spring.framework.DefaultListableBeanFactory;
import com.bbc.spring.framework.reader.DocumentReader;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * @author fitbbc
 * @date 2019/05/20
 */
public class XmlBeanDefinitionReader {

    public void loadBeanDefinition(DefaultListableBeanFactory defaultListableBeanFactory, InputStream inputStream) {
        // 获取document
        DocumentReader documentReader = new DocumentReader();
        Document document = documentReader.createDocument(inputStream);
        //解析document,加载beanDefinition
        XmlBeanDefinitionDocumentParse xmlBeanDefinitionDocumentParse = new XmlBeanDefinitionDocumentParse();
        xmlBeanDefinitionDocumentParse.loadBeanDefinition(defaultListableBeanFactory, document);

    }
}
