package com.bbc.mybatis.framework.builder;

import com.bbc.mybatis.framework.Configuration;
import com.bbc.mybatis.framework.reader.DocumentReader;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

/**
 * 解析全局配置文件
 * User: fitbbc
 * Date: 2019-05-08
 * Time: 18:20
 */
public class XmlConfigBuilder {
    /**
     * 全局配置文件存储对象
     */
    private Configuration configuration;
    /**
     * 连接池方式
     */
    private static final String DBCP = "DBCP";

    public XmlConfigBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 解析全局配置文件
     * @param inputStream
     * @return
     */
    public Configuration parse(InputStream inputStream) {
        // 创建Document对象
        DocumentReader documentReader = new DocumentReader();
        Document document = documentReader.createDocument(inputStream);
        // 根据mybatis语义解析Document对象,封装configuration
        parseConfiguration(document.getRootElement());
        return configuration;
    }

    public Configuration parse(Reader reader) {
        return null;
    }

    /**
     * 解析configuration根标签
     * @param rootElement
     */
    private void parseConfiguration(Element rootElement) {
        //解析environments标签
        parseEnvironmentsElement(rootElement.element("environments"));
        //解析mappers标签
        parseMappersElement(rootElement.element("mappers"));
    }

    /**
     * 解析mappers标签
     * @param element
     */
    private void parseMappersElement(Element element) {
        //获取mapper标签集合
        List<Element> mapperElements = element.elements();
        for (Element mapperElement : mapperElements){
            parseMapperElement(mapperElement);
        }
    }

    /**
     * 解析mapper标签
     * @param element
     */
    private void parseMapperElement(Element element) {
        // 根据映射文件路径读取配置文件
        String resource = element.attributeValue("resource");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        // 解析映射文件
        XmlMapperBuilder xmlMapperBuilder = new XmlMapperBuilder(configuration);
        xmlMapperBuilder.parse(inputStream);
    }

    /**
     * 解析environments标签
     * @param element
     */
    private void parseEnvironmentsElement(Element element) {
        // 获取默认id
        String defaultId = element.attributeValue("default");
        if (defaultId == null || defaultId.equals("")){
            return;
        }
        // 获取environment标签集合
        List<Element> environmentElements = element.elements();
        for (Element envElement: environmentElements){
            String id = envElement.attributeValue("id");
            if (defaultId.equals(id)){
                createDataSource(envElement);
            }
        }
    }

    /**
     * 创建数据源
     * @param element
     */
    private void createDataSource(Element element) {
        // 获取数据源
        Element dataSourceElement = element.element("dataSource");
        // 数据源类型
        String dataSourceType = dataSourceElement.attributeValue("type");

        // 获取数据源链接信息
        List<Element> propertyElements = dataSourceElement.elements("property");
        Properties properties = new Properties();
        for (Element propertyElement: propertyElements){
            String name = propertyElement.attributeValue("name");
            String value = propertyElement.attributeValue("value");
            properties.setProperty(name, value);
        }

        if (DBCP.equals(dataSourceType)){
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setDriverClassName(properties.getProperty("driver"));
            basicDataSource.setUrl(properties.getProperty("url"));
            basicDataSource.setUsername(properties.getProperty("username"));
            basicDataSource.setPassword(properties.getProperty("password"));
            //封装数据源
            configuration.setDataSource(basicDataSource);
        }
    }
}
