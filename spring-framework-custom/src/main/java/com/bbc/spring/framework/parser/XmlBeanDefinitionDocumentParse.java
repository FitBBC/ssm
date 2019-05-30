package com.bbc.spring.framework.parser;

import com.bbc.spring.framework.DefaultListableBeanFactory;
import com.bbc.spring.framework.beandefinition.BeanDefinition;
import com.bbc.spring.framework.beandefinition.PropertyValue;
import com.bbc.spring.framework.beandefinition.RuntimeBeanReference;
import com.bbc.spring.framework.beandefinition.TypedStringValue;
import com.bbc.spring.framework.util.ReflectUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
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
            if (element.getName().equals("bean")){
                //解析bean
                parseBeanElement(element);
            }else{
                parseCustomElement(element);
            }
        }
    }

    private void parseCustomElement(Element element) {
        try {
        //扫描注解包的标签
        if (element.getName().equals("component-scan")){
            String packageName = element.attributeValue("package");
            List<String> classNames = getClassName(packageName, true);
            for (String className: classNames){
                String beanName = Class.forName(className).getSimpleName();
                BeanDefinition beanDefinition = new BeanDefinition(beanName, className);
                //注册beanDefinition
                registerBeanDefinition(beanName, beanDefinition);
            }
        }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取某包下所有类
     *
     * @param packageName
     *            包名
     * @param childPackage
     *            是否遍历子包
     * @return 类的完整名称
     */
    public List<String> getClassName(String packageName, boolean childPackage) {
        List<String> fileNames = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        if (url != null) {
            String type = url.getProtocol();
            if (type.equals("file")) {
                fileNames = getClassNameByFile(url.getPath().replaceAll("%20", " "), packageName,null, childPackage);
            }
        }
        return fileNames;
    }

    /**
     * 从项目文件获取某包下所有类
     *
     * @param filePath
     *            文件路径
     * @param className
     *            类名集合
     * @param childPackage
     *            是否遍历子包
     * @return 类的完整名称
     */
    private List<String> getClassNameByFile(String filePath, String packageName, List<String> className, boolean childPackage) {
        List<String> myClassName = new ArrayList<>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        for (File childFile : childFiles) {
            String fileName = childFile.getName();
            if (childFile.isDirectory()) {
                if (childPackage) {
                    packageName = packageName + "." + fileName;
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), packageName, myClassName, true));
                }
            } else {
                fileName = fileName.substring(0, fileName.lastIndexOf("."));
                myClassName.add(packageName + "." + fileName);
            }
        }

        return myClassName;
    }

    /**
     * 解析bean标签
     * @param element
     */
    private void parseBeanElement(Element element) {
        try {
            String id = element.attributeValue("id");
            String name = element.attributeValue("name");
            String className = element.attributeValue("class");

            String initMethod = element.attributeValue("init-method");

            String beanName = id == null ? name : id;
            beanName = beanName == null ? Class.forName(className).getSimpleName() : beanName;
            //创建beanDefinition信息,根据id和class,设置初始化方法
            BeanDefinition beanDefinition = new BeanDefinition(beanName, className);
            beanDefinition.setInitMethod(initMethod);
            List<Element> propertyElements = element.elements("property");
            for (Element propertyElement: propertyElements){
                //解析property标签
                parsePropertyElement(propertyElement, beanDefinition);
            }
            //注册beanDefinition
            registerBeanDefinition(beanName, beanDefinition);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
