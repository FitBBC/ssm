package com.bbc.mybatis.framework.builder;

import com.bbc.mybatis.framework.Configuration;
import com.bbc.mybatis.framework.MappedStatement;
import com.bbc.mybatis.framework.sqlsource.DefaultSqlSource;
import com.bbc.mybatis.framework.reader.DocumentReader;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;

/**
 * 解析映射文件
 *
 * @auther: fitbbc
 * @date: 2019/05/08
 */
public class XmlMapperBuilder {

    private Configuration configuration;

    public XmlMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) {
        // 创建Document对象
        DocumentReader documentReader = new DocumentReader();
        Document document = documentReader.createDocument(inputStream);
        // 解析Mappers标签
        parseMapperElement(document.getRootElement());
    }

    /**
     * 解析Mappers标签
     * @param rootElement
     */
    private void parseMapperElement(Element rootElement) {
        // 获取工作空间
        String namespace = rootElement.attributeValue("namespace");
        // 获取select标签集合
        List<Element> selectElements = rootElement.elements("select");
        for (Element selectElement : selectElements){
            parseStatement(namespace, selectElement);
        }
    }

    /**
     * 解析select标签,然后封装
     * @param namespace
     * @param element
     */
    private void parseStatement(String namespace, Element element) {
        String id = element.attributeValue("id");
        // 入参类型
        String parameterType = element.attributeValue("parameterType");
        Class<?> parameterTypeClass = getTypeClass(parameterType);
        // 映射结果类型
        String resultType = element.attributeValue("resultType");
        Class<?> resultTypeClass = getTypeClass(resultType);

        String statementType = element.attributeValue("statementType");
        //获取未解析#{}的sql语句
        String sqlText = element.getTextTrim();
        DefaultSqlSource sqlSource = new DefaultSqlSource(sqlText, parameterTypeClass);
        //将解析的信息封装到MappedStatement对象中 
        MappedStatement mappedStatement = new MappedStatement(id, parameterTypeClass, resultTypeClass, statementType, sqlSource);
        //将MappedStatement封装到configuration对象中
        configuration.addMappedStatement(namespace + "." + id, mappedStatement);
    }

    private Class<?> getTypeClass(String name){
        Class<?> clazz = null;
        try {
            clazz = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }
}
