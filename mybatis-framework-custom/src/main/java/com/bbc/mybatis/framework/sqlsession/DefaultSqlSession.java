package com.bbc.mybatis.framework.sqlsession;

import com.bbc.mybatis.framework.Configuration;
import com.bbc.mybatis.framework.MappedStatement;
import com.bbc.mybatis.framework.ParameterMapping;
import com.bbc.mybatis.framework.sqlsource.BoundSql;
import com.bbc.mybatis.framework.sqlsource.SqlSource;
import com.bbc.mybatis.framework.statement.StatementHandle;
import com.bbc.mybatis.framework.statement.StatementHandleFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/13
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private Connection connection;

    public DefaultSqlSession(Configuration configuration, Connection connection) {
        this.configuration = configuration;
        this.connection = connection;
    }

    @Override
    public <T> T selectOne(String statementId, Object parameterObject) throws Exception {
        List<T> list = this.selectList(statementId, parameterObject);
        if (list.size() == 1){
            return list.get(0);
        }else if (list.size() > 1){
            throw new Exception("返回结果多条");
        }else{
            return null;
        }
    }

    @Override
    public <T> List<T> selectList(String statementId, Object parameterObject) {
        //获取statement对象
        MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        //获取sqlsource,里面封装了未解析的sql语句(包含#{})
        SqlSource sqlSource = mappedStatement.getSqlSource();
        //获取boundsql, 里面封装解析过的sql语句(将#{} 替换成 ?)和参数信息集合(参数名称和参数类型class)
        BoundSql boundSql = sqlSource.getBoundSql();

        StatementHandle statementHandle = new StatementHandleFactory().getStatementHandle(mappedStatement, boundSql, parameterObject);
        //预编译
        Statement statement = statementHandle.prepare(connection);
        //设置参数
        statementHandle.parameterize(statement);
        return statementHandle.query(statement);
    }

    @Override
    public <T> T insert(String statementId, Object parameterObject) {
        return null;
    }

    @Override
    public <T> T update(String statementId, Object parameterObject) {
        return null;
    }

    @Override
    public Integer delete(String statementId, Object parameterObject) {
        return null;
    }
}
