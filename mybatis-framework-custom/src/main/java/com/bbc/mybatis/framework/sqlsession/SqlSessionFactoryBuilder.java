package com.bbc.mybatis.framework.sqlsession;

import com.bbc.mybatis.framework.Configuration;
import com.bbc.mybatis.framework.builder.XmlConfigBuilder;

import java.io.InputStream;
import java.io.Reader;

/**
 * @author fitbbc
 * @date 2019/05/13
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader){
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parse(reader);
        return build(configuration);
    }

    public SqlSessionFactory build(InputStream inputStream){
        XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parse(inputStream);
        return build(configuration);
    }

    private SqlSessionFactory build(Configuration configuration){
        return new DefaultSqlSessionFactory(configuration);
    }
}
