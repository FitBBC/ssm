package com.bbc.mybatis.framework.resultset;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/15
 */
public interface ResultSetHandle {

    public <T> List<T> handleResultSets(Statement statement);
}
