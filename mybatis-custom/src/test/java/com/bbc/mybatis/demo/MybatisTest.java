package com.bbc.mybatis.demo;

import com.bbc.mybatis.framework.Configuration;
import com.bbc.mybatis.framework.builder.XmlConfigBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * Description:
 * User: fitbbc
 * Date: 2019-05-08
 * Time: 16:06
 */
public class MybatisTest {

    @Test
    public void test(){
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);

        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();

        Configuration configuration = xmlConfigBuilder.parse(inputStream);
        System.out.println(configuration);
    }
}
