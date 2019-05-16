package com.bbc.mybatis.framework.statement;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/14
 */
public interface StatementHandle {

    public <T> List<T> query(Statement statement);

    public Statement prepare(Connection connection);

    public void parameterize(Statement statement);
}
