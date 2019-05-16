package com.bbc.mybatis.framework.statement;

import com.bbc.mybatis.framework.MappedStatement;
import com.bbc.mybatis.framework.sqlsource.BoundSql;

/**
 * @author fitbbc
 * @date 2019/05/14
 */
public class StatementHandleFactory {

    public StatementHandle getStatementHandle(MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject){
        switch (mappedStatement.getStatementType()){
            case "prepared": return new PreparedStatementHandle(mappedStatement, boundSql, parameterObject);
        }
        return null;
    }
}
