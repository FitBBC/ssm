package com.bbc.mybatis.framework.sqlsession;

import com.bbc.mybatis.framework.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author fitbbc
 * @date 2019/05/13
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        try {
            //获取数据源
            DataSource dataSource = configuration.getDataSource();
            //创建数据库连接
            Connection connection = dataSource.getConnection();
            return new DefaultSqlSession(configuration, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
