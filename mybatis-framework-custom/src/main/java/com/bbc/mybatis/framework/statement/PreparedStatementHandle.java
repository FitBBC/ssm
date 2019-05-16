package com.bbc.mybatis.framework.statement;

import com.bbc.mybatis.framework.MappedStatement;
import com.bbc.mybatis.framework.parameter.DefaultParameterHandle;
import com.bbc.mybatis.framework.parameter.ParameterHandle;
import com.bbc.mybatis.framework.resultset.DefaultResultSetHandle;
import com.bbc.mybatis.framework.resultset.ResultSetHandle;
import com.bbc.mybatis.framework.sqlsource.BoundSql;

import java.sql.*;
import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/14
 */
public class PreparedStatementHandle implements StatementHandle {

    private BoundSql boundSql;

    private ParameterHandle parameterHandle;

    private ResultSetHandle resultSetHandle;

    private Object parameterObject;

    public PreparedStatementHandle(MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) {
        this.boundSql = boundSql;
        this.parameterObject = parameterObject;
        this.parameterHandle = new DefaultParameterHandle(parameterObject, mappedStatement, boundSql);
        this.resultSetHandle = new DefaultResultSetHandle(mappedStatement);
    }

    @Override
    public <T> List<T> query(Statement statement) {
        PreparedStatement preparedStatement = (PreparedStatement) statement;
        try {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSetHandle.handleResultSets(preparedStatement);
    }

    @Override
    public Statement prepare(Connection connection) {
        try {
            return connection.prepareStatement(this.boundSql.getSql());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void parameterize(Statement statement) {
        this.parameterHandle.setParameters((PreparedStatement) statement);
    }
}
